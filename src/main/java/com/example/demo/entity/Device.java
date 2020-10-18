package com.example.demo.entity;

import lombok.Data;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Table(name="devices")
public class Device {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private  int id;


    @Column(name = "project_id")
    private int project_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    public  Project project;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "id", nullable = false)
//    public Event event;

    @Column(name = "serial_number")
    private String serial_number;

    public Device(){}
//    public Device(int id, )

//    public JSONObject getAsJson(){
////        JSONObject jsonObject = new JSONObject();
//
//        JSONObject device = new JSONObject();
//        device.put("id", getId());
//        device.put("project_id", getProject_id());
//
////        jsonObject.put(get)
//        return  device;
//    }


}