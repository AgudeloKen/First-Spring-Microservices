package com.ken.school;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullSchoolResponse {

    private Long id;
    private String name;
    private String email;
    private List<Student> students;
}
