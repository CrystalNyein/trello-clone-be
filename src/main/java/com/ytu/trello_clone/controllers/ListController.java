package com.ytu.trello_clone.controllers;

import com.ytu.trello_clone.repositories.ListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ytu.trello_clone.models.List;

@RestController
@CrossOrigin
public class ListController {
    @Autowired
    private ListRepository listRepository;

    @GetMapping("/list")
    public java.util.List<List> getAll(){
        return listRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public List getById(@PathVariable Long id){
        return listRepository.getOne(id);
    }

    @PostMapping("/list")
    public List create(@RequestBody List list){
        return listRepository.saveAndFlush(list);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/list/{listId}")
    public List update(@PathVariable Long listId,@RequestBody List list){
        List oldList=listRepository.getOne(listId);
        BeanUtils.copyProperties(list,oldList,"id","dateCreated");
        return listRepository.saveAndFlush(oldList);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/list/{listId}")
    public void delete(@PathVariable Long listId){
        listRepository.deleteById(listId);
    }

    @GetMapping("/list/position/{position}")
    public java.util.List<List> getPositionGTE(@PathVariable Integer position){
        System.out.print(position);
        return listRepository.findByPositionGreaterThanEqual(position);
    }

    @GetMapping("/list/title/{title}")
    public java.util.List<List> searchByTitle(@PathVariable String title){
        return listRepository.findByTitleContaining(title);
    }

    @GetMapping("/list/status/{status}")
    public java.util.List<List> searchByStatus(@PathVariable Integer status){
        return listRepository.findByStatus(status);
    }


}
