package com.alanser.marks.web.controller;

import com.alanser.marks.service.MarksService;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class MarksControllerTest {

  @Autowired
  MarksService marksService;

  private static final String BASE_URL = "http://localhost:8080";

  private RestTemplate restTemplate = new RestTemplate();

  @Test
  public void givenFile_whenImport_thenSuccess() throws Exception {
    MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
    bodyMap.add("file", new FileSystemResource(new File("marks.txt")));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

    ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/upload", requestEntity, String.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
  }

  @Test
  public void givenGrade_whenGetAverageEstimatePerMonth_thenSuccess() {
    ResponseEntity<ArrayList> response = restTemplate
        .getForEntity(BASE_URL + "/average-estimate-per-month/5", ArrayList.class);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    Assertions.assertEquals(3, response.getBody().size());
  }

  @Test
  public void givenGrade_whenGetAverageEstimatePerMonth_thenNotFound() {
    try {
      restTemplate.getForEntity(BASE_URL + "/average-estimate-per-month/10", String.class);
    } catch (Exception e) {
      Assertions.assertEquals("404 : [Класс не найден]", e.getMessage());
    }
  }

  @Test
  public void givenGrade_whenGetAverageEstimate_thenSuccess() {
    ResponseEntity<Map> response = restTemplate.getForEntity(BASE_URL + "/average-estimate/5", Map.class);
    System.out.println(response);
  }

  @Test
  public void givenGrade_whenGetAverageEstimate_thenNotFound() {
    try {
      restTemplate.getForEntity(BASE_URL + "/average-estimate/11", String.class);
    } catch (Exception e) {
      Assertions.assertEquals("404 : [Класс не найден]", e.getMessage());
    }
  }
}