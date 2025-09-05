package org.ecommerce.gateway.controller;

import org.ecommerce.gateway.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${keycloak.server.url}")
    private String keycloakServerUrl;

    @Value("${keycloak.client.id}")
    private String clientId;

    @Value("${keycloak.client.secret}")
    private String clientSecret;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        String url = keycloakServerUrl + "/admin/realms/micro-services/users";

        Map<String, Object> newUser = Map.of(
                "username", userDto.getUsername(),
                "email", userDto.getEmail(),
                "enabled", true,
                "credentials", List.of(Map.of(
                        "type", "password",
                        "value", userDto.getPassword(),
                        "temporary", false
                ))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAdminToken());
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(newUser, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getStatusCode().is2xxSuccessful()
                ? ResponseEntity.ok("User registered successfully!")
                : ResponseEntity.status(response.getStatusCode()).body("Failed to register user");
    }

    private String getAdminToken() {
        String url = keycloakServerUrl + "/realms/micro-services/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("grant_type", "password");
        params.add("username", "admin");
        params.add("password", "admin123");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody() != null ? (String) response.getBody().get("access_token") : null;
    }
}
