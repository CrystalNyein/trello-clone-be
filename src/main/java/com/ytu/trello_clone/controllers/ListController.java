package com.ytu.trello_clone.controllers;

import com.ytu.trello_clone.repositories.ListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ytu.trello_clone.models.List;

@RestController
@CrossOrigin
@RequestMapping("list")
public class ListController {
    @Autowired
    ListRepository listRepository;

    @GetMapping
    public java.util.List<List> getAll(){
        return listRepository.findAll();
    }

    @GetMapping("{id}")
    public List getById(@PathVariable Long id){
        return listRepository.getOne(id);
    }

    @GetMapping("notAchive")
    public java.util.List<List> getNotAchive(){
        return listRepository.findByStatus(1);
    }

    @PostMapping
    public List create(@RequestBody List list){
        return listRepository.saveAndFlush(list);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{listId}")
    public List update(@PathVariable Long listId,@RequestBody List list){
        List oldList=listRepository.getOne(listId);
        BeanUtils.copyProperties(list,oldList,"id","dateCreated");
        return listRepository.saveAndFlush(oldList);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{listId}")
    public void delete(@PathVariable Long listId){
        listRepository.deleteById(listId);
    }

    @GetMapping("position/{position}")
    public java.util.List<List> getPositionGTE(@PathVariable Integer position){
        System.out.print(position);
        return listRepository.findByPositionGreaterThanEqual(position);
    }

    @GetMapping("title/{title}")
    public java.util.List<List> searchByTitle(@PathVariable String title){
        return listRepository.findByTitleContaining(title);
    }

    @GetMapping("/achive/{id}")
    public List setAchive(@PathVariable Long id){
        List list=listRepository.getOne(id);
        list.setStatus(2);
        return listRepository.saveAndFlush(list);
    }
    @GetMapping("/unachive/{id}")
    public List setUnachive(@PathVariable Long id){
        List list=listRepository.getOne(id);
        list.setStatus(1);
        return listRepository.saveAndFlush(list);
    }
    @GetMapping("/delete/{id}")
    public List setDelete(@PathVariable Long id){
        List list=listRepository.getOne(id);
        list.setStatus(3);
        return listRepository.saveAndFlush(list);
    }

}
