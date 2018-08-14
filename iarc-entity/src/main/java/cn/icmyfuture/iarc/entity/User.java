package cn.icmyfuture.iarc.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private int id;
    private String name;
    private Address address;
}
