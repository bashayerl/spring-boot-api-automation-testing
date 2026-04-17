package com.bashayer.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Entity
@Table(name = "users") //name of table on SQL
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ترقيم تلقائي
    private Long id;
    @NotBlank(message = "الأسم لا يمكن ان يكون فارغا")
    @Size(min = 2,message = "الأسم يجب ان يكون من حرفين")
    private String name;
    @NotBlank(message = "البريد ألإلكتروني مطلوب")
    @Email(message = "يجب إدخال البريد إلكتروني بصيغة صحيحة")
    private String email;
    //constructors
    public  User() {
    }
    public User(String name) {
        this.name = name;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}

