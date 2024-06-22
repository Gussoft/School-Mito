package com.gussoft.school_mito.integration.transfer.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse implements Serializable {

    private String id;

    private String name;
    private String acronym;
    private Boolean status;

}
