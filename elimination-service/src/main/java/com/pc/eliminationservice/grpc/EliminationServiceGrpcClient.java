package com.pc.eliminationservice.grpc;

import com.sih.backendService.grpc.MigrantIdRequest;
import com.sih.backendService.grpc.MigrantResponse;
import com.sih.backendService.grpc.MigrantServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminationServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(EliminationServiceGrpcClient.class);

    private final MigrantServiceGrpc.MigrantServiceBlockingStub blockingStub;


    public EliminationServiceGrpcClient(@Value("${migrant.service.address}") String serverAddress,
                                        @Value("${migrant.service.grpc.port}") int serverPort) {

        log.info("Connecting to billing service GRPC service at {}:{}",serverAddress,serverPort);

        ManagedChannel channel= ManagedChannelBuilder.forAddress(serverAddress,serverPort)
                .usePlaintext().build();

        this.blockingStub=MigrantServiceGrpc.newBlockingStub(channel);
    }

    public MigrantResponse getPatientById(UUID migrantHealthId){
        log.info("request came to client processing before sending to server");
        MigrantIdRequest request=MigrantIdRequest.newBuilder()
                .setMigrantId(migrantHealthId.toString())
                .build();
        MigrantResponse response=blockingStub.getMigrantById(request);
        log.info("Received Response from Migrant service via GRPC: {}", response);
        return response;
    }
}
