package com.ytu.trello_clone.repositories;

import com.ytu.trello_clone.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    public List<Card> findByListId(Long listId);
    public List<Card> findByTitleContaining(String title);
    public List<Card> findByStatus(Integer status);
}
