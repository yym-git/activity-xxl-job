package com.bc.test.job.activityxxljob.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ym.y
 * @description
 * @date 16:59 2022/7/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private String name;
    private int  age;
    private double salary;
    private Status status;
}
