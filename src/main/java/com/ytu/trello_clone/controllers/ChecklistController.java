package com.ytu.trello_clone.controllers;

import java.util.List;

import com.ytu.trello_clone.models.Checklist;
import com.ytu.trello_clone.repositories.CardRepository;
import com.ytu.trello_clone.repositories.ChecklistRepository;

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
@RequestMapping("checklist")
public class ChecklistController{

    @Autowired
    ChecklistRepository checklistRepository;

    @Autowired
    CardRepository cardRepository;

    @GetMapping("{cardId}")
    public List<Checklist> getAllByCard(@PathVariable Long cardId){
        return checklistRepository.findByCard(cardRepository.getOne(cardId));
    }
    
    @PostMapping
    public Checklist save(@RequestBody Checklist checklist){
        Long id=checklist.getCard().getId();
        checklist.setCard(cardRepository.getOne(id));
        return checklistRepository.saveAndFlush(checklist);        
    }

    @RequestMapping(method = RequestMethod.PUT,value="{id}")
    public Checklist update(@RequestBody Checklist checklist,@PathVariable Long id){
        Checklist oldChecklist=checklistRepository.getOne(id);
        BeanUtils.copyProperties(checklist,oldChecklist,"id","dateCreated");
        oldChecklist.setCard(cardRepository.getOne(checklist.getCard().getId()));
        return checklistRepository.saveAndFlush(oldChecklist);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    public void delete(@PathVariable Long id){
        checklistRepository.deleteById(id);
    }
}