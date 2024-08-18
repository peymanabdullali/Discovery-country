package com.example.discovery_country.model.dto.request.auth;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
