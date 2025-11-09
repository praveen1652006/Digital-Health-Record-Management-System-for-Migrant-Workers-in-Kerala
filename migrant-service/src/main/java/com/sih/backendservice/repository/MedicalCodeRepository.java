package com.sih.backendservice.repository;

import com.sih.backendservice.eks.MedicalCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalCodeRepository extends ElasticsearchRepository<MedicalCode,Integer> {

    @Query("{\"match_phrase_prefix\": {\"description\": {\"query\": \"?0\"}}}")
    List<MedicalCode> searchByTerm(String prefix, Pageable pageable);


    // Fuzzy full-text search "maleriya" → "malaria"
    @Query("{\"match\": {\"description\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    List<MedicalCode> searchByDescriptionFuzzy(String term);
}
