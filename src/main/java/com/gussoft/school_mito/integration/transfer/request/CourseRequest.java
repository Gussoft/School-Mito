package com.gussoft.school_mito.integration.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest implements Serializable {

    private String id;

    private String name;
    private String acronym;
    private Boolean status;

}
