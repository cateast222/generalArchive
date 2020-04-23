package com.ebs.platform.business.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private static final long serialVersionUID = -8366929034564774130L;

    private String id;

    private String name;
}