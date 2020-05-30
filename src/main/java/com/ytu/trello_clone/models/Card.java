package com.ytu.trello_clone.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Card extends MainModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date due_date;
    private Integer position;
    private Integer status=1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="list_id",nullable = false)
    @JsonIgnoreProperties("cards")    
    private List list;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE,mappedBy = "card")
    @JsonIgnoreProperties("card")
    private Set<Checklist> checkList;

    @ManyToMany
    @JoinTable(
            name="card_member",
            inverseJoinColumns = @JoinColumn(name = "account_username"),
            joinColumns = @JoinColumn(name="card_id")
    )
    private Set<Account> members=new HashSet<Account>();

    @ManyToMany
    @JoinTable(
        name = "card_label",
        joinColumns = @JoinColumn(name="card_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<Label> labels=new HashSet<Label>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Set<Account> getMembers() {
        return members;
    }

    public void setMembers(Set<Account> members) {
        this.members = members;
    }

    public Set<Checklist> getCheckList() {
        return checkList;
    }

    public void setCheckList(Set<Checklist> checkList) {
        this.checkList = checkList;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

}
