package com.ytu.trello_clone.repositories;

import com.ytu.trello_clone.models.Label;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long>{
    
}