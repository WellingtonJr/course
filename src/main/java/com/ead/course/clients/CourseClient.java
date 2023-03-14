package com.ead.course.clients;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ead.course.dtos.ResponsePageDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.services.UtilsService;

@Component
public class CourseClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UtilsService utilsService;

    @Value("${ead.api.url.authuser}")
    String REQUEST_URL_AUTHUSER;

    public Page<UserDto> getAllUsersByCourse(UUID courseId, Pageable pageable) {
        // List<CourseDto> searchResult = null; //APENAS PARA LOG
        ResponseEntity<ResponsePageDto<UserDto>> result = null;
        String url = REQUEST_URL_AUTHUSER + utilsService.createUrlGetAllUsersByCourse(courseId, pageable);

        try {
            ParameterizedTypeReference<ResponsePageDto<UserDto>> responseType = new ParameterizedTypeReference<ResponsePageDto<UserDto>>() {
            };
            result = restTemplate.exchange(url, HttpMethod.GET, null,
                    responseType);
            // searchResult = result.getBody().getContent();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("ERROR: " + e.getMessage());
        }

        return result.getBody();
    }

}
