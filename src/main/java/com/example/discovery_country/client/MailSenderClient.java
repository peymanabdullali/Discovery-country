package com.example.discovery_country.client;

import com.example.discovery_country.model.dto.request.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "ms-mailsender", url = "http://localhost:8081")
public interface MailSenderClient {
    @PostMapping("/send")
    ResponseEntity<Void> sendMail(@RequestBody UserRequest userRequest);
}