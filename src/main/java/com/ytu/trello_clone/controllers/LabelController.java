package com.ytu.trello_clone.controllers;

import java.util.List;

import com.ytu.trello_clone.models.Label;
import com.ytu.trello_clone.repositories.LabelRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("label")
public class LabelController {
    @Autowired
    LabelRepository labelRepository;

    @GetMapping
    public List<Label> getAll(){
        return labelRepository.findAll();
    }

    @PostMapping
    public Label create(@RequestBody Label label){
        return labelRepository.saveAndFlush(label);
    }

    @GetMapping("{id}")
    public Label getById(@PathVariable Long id){
        return labelRepository.getOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "{id}")
    public Label update(@RequestBody Label label,@PathVariable Long id){
        Label oldLabel=labelRepository.getOne(id);
        BeanUtils.copyProperties(label, oldLabel,"id","dateCreated");
        return labelRepository.saveAndFlush(oldLabel);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    public void delete(@PathVariable Long id){
        labelRepository.deleteById(id);
    }
}