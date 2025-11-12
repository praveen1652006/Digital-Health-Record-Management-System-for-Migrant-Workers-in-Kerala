package com.sih.backendservice.grpc;

import com.sih.backendService.grpc.MigrantIdRequest;
import com.sih.backendService.grpc.MigrantResponse;
import com.sih.backendService.grpc.MigrantServiceGrpc;
import com.sih.backendservice.model.Migrant;
import com.sih.backendservice.repository.MigrantRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class MigrantGrpcService extends MigrantServiceGrpc.MigrantServiceImplBase {

    private static final Logger log= LoggerFactory.getLogger(MigrantGrpcService.class);

    private final MigrantRepository migrantRepository;

    @Override
    @Transactional(readOnly = true)
    public void getMigrantById(MigrantIdRequest request,
                               StreamObserver<MigrantResponse> responseObserver) {

        try{
            log.info("Try processing the migrant if there exist or not");
            if(request.getMigrantId()==null || request.getMigrantId().isEmpty()){
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Migrant ID is required")
                        .asRuntimeException());
                return;
            }

            UUID migrantHealthId = UUID.fromString(request.getMigrantId());
            Optional<Migrant> migrantOptional=migrantRepository.findByMigrantHealthId(migrantHealthId);

            MigrantResponse.Builder response=MigrantResponse.newBuilder()
                    .setMigrantId(migrantHealthId.toString());

            if(migrantOptional.isEmpty()){
                log.info("Migrant not exists");
                response.setName("NOT_FOUND");
                response.setExists(false);
            }
            else{
                log.info("Migrant Exists processing the response to send to the client");
                Migrant migrant=migrantOptional.get();
                response.setExists(true);
                response.setName(migrant.getName());
            }

            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }
        catch (IllegalArgumentException e){
            // Invalid UUID Format
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid Migrant ID Format")
                    .asRuntimeException());
        } catch (Exception e) {
            log.info("Dont know why this error came");
            responseObserver.onError(Status.INTERNAL.augmentDescription("Unexpected server error")
                    .asRuntimeException());
        }
    }
}
