package com.ead.course.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ead.course.repositories.CourseUserRepository;
import com.ead.course.services.CourseUserService;

public class CourseUserServiceImpl implements CourseUserService {

    @Autowired
    CourseUserRepository courseUserRepository;

}
