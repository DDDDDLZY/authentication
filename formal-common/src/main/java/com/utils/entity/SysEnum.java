package com.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysEnum implements Serializable {
    public Integer taskStatus_done = 1;
    public Integer taskStatus_no = 2;
    public Integer taskStatus_del = -1;
}
