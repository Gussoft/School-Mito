package com.gussoft.school_mito.integration.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    private String id;

    private LocalDateTime date;

    private StudentRequest student;

    private List<CourseRequest> courses;

    private Boolean status;
}
