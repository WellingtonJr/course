package com.ead.course.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.CourseUserRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseUserRepository courseUserRepository;

    @Transactional
    @Override
    public void delete(CourseModel course) {
        List<ModuleModel> moduleModelList = moduleRepository.finddAllModulesIntoCourse(course.getCourseId());
        if (!moduleModelList.isEmpty()) {
            for (ModuleModel module : moduleModelList) {
                List<LessonModel> lessonModuleList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
                if (!lessonModuleList.isEmpty()) {
                    lessonRepository.deleteAll(lessonModuleList);
                }
            }
            moduleRepository.deleteAll(moduleModelList);
        }
        List<CourseUserModel> courseUserModelList = courseUserRepository
                .findAllCourseUserIntoCourse(course.getCourseId());
        if (!courseUserModelList.isEmpty()) {
            courseUserRepository.deleteAll(courseUserModelList);
        }

        courseRepository.delete(course);
    }

    @Override
    public CourseModel save(CourseModel courseModel) {
        return courseRepository.save(courseModel);
    }

    @Override
    public Optional<CourseModel> findById(UUID courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable) {
        return courseRepository.findAll(spec, pageable);
    }

}
