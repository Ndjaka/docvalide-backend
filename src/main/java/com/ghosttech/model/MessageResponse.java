package com.ghosttech.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MessageResponse {
    private boolean success;
    private DataInfo data;
    private String message;

    @Setter
    @Getter
    public static class DataInfo {
        private int credit_sms;
        private String type_de_compte;
        private boolean isBan;
        private boolean isAdmin;
        private List<String> sub_account;
        private String _id;
        private String first_name;
        private String email;
        private String last_name;
        private String phone;
        private String password;
        private String city;
        private String country;
        private String sender_sms_name;
        private String createdAt;
        private String updatedAt;
        private String slug;
        private int __v;
        private String id;
    }
}
