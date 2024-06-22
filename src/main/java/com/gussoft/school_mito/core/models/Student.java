package com.gussoft.school_mito.core.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field
    private String name;
    @Field
    private String lastName;
    @Field
    private String dni;
    @Field
    private Integer age;

}
