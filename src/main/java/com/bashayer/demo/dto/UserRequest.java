package com.bashayer.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotBlank(message = "الاسم لا يمكن أن يكون فارغًا")
    @Size(min = 2, message = "الاسم يجب أن يكون من حرفين على الأقل")
    private String name;

    @NotBlank(message = "البريد الإلكتروني مطلوب")
    @Email(message = "يجب إدخال البريد الإلكتروني بصيغة صحيحة")
    private String email;

    public UserRequest() {
    }

    public UserRequest(String name, String email) {
        this.name = name;
        this.email = email;
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