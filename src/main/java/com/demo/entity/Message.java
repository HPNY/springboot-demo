package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    private Integer id;

    private String username;

    private String userId;


}
