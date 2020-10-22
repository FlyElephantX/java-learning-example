package com.example.rabbitmq.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message<T> implements Serializable {
    public String id;
    public T content;
}
