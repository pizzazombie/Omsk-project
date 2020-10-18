package com.example.demo.entity;



import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "device_id")
    private Integer device_id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "type")
    private String type;

    @Column(name = "is_read")
    private boolean is_read;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "device_id", nullable = false , insertable = false, updatable = false)
    public  Device device;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public  Project project;
}
