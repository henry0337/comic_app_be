package com.henry.demo.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDTO {
    private String name;
    private String description;
    private String avatar;
    private Date birthday;
}
