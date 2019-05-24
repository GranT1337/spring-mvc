package com.ostanin.dto;

import lombok.Data;

@Data
public class Film {
    private long id;
    private String title;
    private String producer;
    private double points;
}
