package org.lazydog.comicbox.controller;

import java.util.List;
import org.lazydog.comicbox.model.Grade;
import org.lazydog.comicbox.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Grade controller.
 *
 * @author rjrjr
 */
@RestController
@RequestMapping(value = "/grades", produces = {MediaType.APPLICATION_JSON_VALUE})
public class GradeController {

    @Autowired
    private GradeRepository repository;

    @GetMapping("")
    public List<Grade> getGrades() {
        return this.repository.findAll();
    }
}
