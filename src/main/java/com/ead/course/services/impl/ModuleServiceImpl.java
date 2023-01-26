package com.ead.course.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(ModuleModel module) {
        List<LessonModel> lessonModuleList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
        if (!lessonModuleList.isEmpty()) {
            lessonRepository.deleteAll(lessonModuleList);
        }
        moduleRepository.delete(module);
    }
}
