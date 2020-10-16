package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Table(name="projects")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "device_id", nullable = false)
    public Device device;

    private String name;

}