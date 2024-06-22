package com.gussoft.school_mito.core.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "registration")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Registration implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field
    private LocalDateTime date;


    private Student student;


    private List<Course> courses;

    @Field
    private Boolean status;

}
