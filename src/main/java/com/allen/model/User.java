package com.allen.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : allengent@163.com
 * @date : 2019/8/29 22:07
 * description :
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;


    private String name;



    private Integer age;



    private BigDecimal money;



    private LocalDateTime updatedAt;


    private LocalDateTime createdAt;
}
