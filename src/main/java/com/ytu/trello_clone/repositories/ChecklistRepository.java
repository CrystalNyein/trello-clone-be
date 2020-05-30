package com.ytu.trello_clone.repositories;

import java.util.List;

import com.ytu.trello_clone.models.Card;
import com.ytu.trello_clone.models.Checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist,Long>{
    public List<Checklist> findByCard(Card card);
    
}