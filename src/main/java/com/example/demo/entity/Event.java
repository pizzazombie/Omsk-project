package com.example.demo.entity;



import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer device_id;

    private Date date;

    private String type;

    private boolean is_read;
}
