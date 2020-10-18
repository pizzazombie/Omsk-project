package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "device_id", nullable = false, insertable = false, updatable = false)
//    public Device device;

    @Column(name = "name")
    private String name;

    private int deviceCount;
    private int deviceWithErrors;
    private int stableDevices;



}