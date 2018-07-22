package com.minxc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Student
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/22 15:11
 * @Version 1.0
 **/

@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class Student {
    private int age;
    private int id;
    private String name;
    private String sumScore;
    private String avgScore;
}
