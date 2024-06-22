package com.gussoft.school_mito.integration.transfer.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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
public class RegistrationResponse implements Serializable {

    private String id;

    private LocalDateTime date;

    private StudentResponse student;

    private List<CourseResponse> courses;

    private Boolean status;

}
