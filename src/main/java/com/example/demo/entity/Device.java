package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Table(name="devices")
public class Device {

    @Id
    private  int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "project_id", nullable = false)
    public Project project;

//    @Column(name = "serial_number")
    private String serial_number;



}