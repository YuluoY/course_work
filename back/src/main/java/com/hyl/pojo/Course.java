package com.hyl.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;
    private String name;
    private String type;
    @JsonProperty("teacher_id")
    private int teacherId;
    @DateTimeFormat
    private Date startTime;
    @DateTimeFormat
    private Date endTime;
}
