package com.ghosttech.service;

import com.ghosttech.model.MessageRequest;
import com.ghosttech.model.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.ghosttech.constants.MessageConstant.API_SMS_URL;


@Service
public class MessageService {

    private final RestTemplate restTemplate;

    public MessageService() {
        this.restTemplate = new RestTemplate();
    }


    public MessageResponse sendMessage(MessageRequest messageRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageRequest> requestEntity = new HttpEntity<>(messageRequest, headers);

        ResponseEntity<MessageResponse> responseEntity = restTemplate.postForEntity(API_SMS_URL, requestEntity, MessageResponse.class);

        return responseEntity.getBody();
    }
}
