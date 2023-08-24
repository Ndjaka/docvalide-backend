package com.ghosttech.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String user_id;
    private String message;
    private String password;
    private String phone_str;
    private String sender_name;
}
