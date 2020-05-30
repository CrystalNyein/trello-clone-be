package com.ytu.trello_clone.repositories;

import com.ytu.trello_clone.models.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List,Long> {
    public java.util.List<List> findByPositionGreaterThanEqual(Integer position);
    public java.util.List<List> findByTitleContaining(String title);
    public java.util.List<List> findByStatus(Integer status);
}
