package com.bashayer.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users") // name of table on SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ترقيم تلقائي
    private Long id;

    @NotBlank(message = "الاسم لا يمكن أن يكون فارغًا")
    @Size(min = 2, message = "الاسم يجب أن يكون من حرفين على الأقل")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "البريد الإلكتروني مطلوب")
    @Email(message = "يجب إدخال البريد الإلكتروني بصيغة صحيحة")
    @Column(nullable = false, unique = true)
    private String email;

    // Constructors
    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
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