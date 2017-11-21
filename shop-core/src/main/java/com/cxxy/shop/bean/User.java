package com.cxxy.shop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true)
    private String user_name;

    @Column
    private String password;

    @Column
    private Long class_id;

    @ManyToOne
    @JoinColumn(name = "class_id",referencedColumnName = "class_id",insertable = false,updatable = false)
    private SClass sClass;


    public User(Long user_id,String user_name,Long class_id,SClass sClass){
        super();
        this.user_id = user_id;
        this.user_name = user_name;
        this.class_id = class_id;
        this.sClass = sClass;
    }
}
