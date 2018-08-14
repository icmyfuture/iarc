package cn.icmyfuture.iarc.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Address implements Serializable {
    private String country;
    private String province;
    private String city;
    private String street;
}
