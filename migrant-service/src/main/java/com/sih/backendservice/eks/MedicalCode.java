package com.sih.backendservice.eks;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="medical_codes")
@Getter
@Setter
public class MedicalCode {
    @Id
    private Integer id;

    @Field(type = FieldType.Keyword)
    private String code;

    @Field(type = FieldType.Text)
    private String description;
}