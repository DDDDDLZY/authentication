package com.utils.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private Integer code;
    private String introduction;
    private String avatar;
    private String name;
    private List roles;
}
