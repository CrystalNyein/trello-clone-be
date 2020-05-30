package com.ytu.trello_clone.controllers;

import com.ytu.trello_clone.models.Account;
import com.ytu.trello_clone.models.Card;
import com.ytu.trello_clone.models.Label;
import com.ytu.trello_clone.repositories.AccountRepository;
import com.ytu.trello_clone.repositories.CardRepository;
import com.ytu.trello_clone.repositories.LabelRepository;
import com.ytu.trello_clone.repositories.ListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("card")
public class CardController {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    ListRepository listRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LabelRepository labelRepository;

    @GetMapping
    public List<Card> getAll(){
        return cardRepository.findAll();
    }

    @GetMapping("{id}")
    public Card getById(@PathVariable Long id){
        return cardRepository.getOne(id);
    }

    @GetMapping("/byList/{listId}")
    public List<Card> getAllCardsByListId(@PathVariable Long listId){
        return cardRepository.findByListId(listId);
    }

    @PostMapping
    public Card create(@RequestBody Card card){
        Long id=card.getList().getId();
        card.setList(listRepository.getOne(id));
        return cardRepository.saveAndFlush(card);

    }

    @PostMapping("add-member")
    public Card addMember(@RequestBody Map<String,Object> member){
        Card card=cardRepository.getOne(Long.parseLong(member.get("card_id").toString()));
        Set<Account> members=card.getMembers();
        if(members==null){
            members=new HashSet<Account>();
        }
        members.add(accountRepository.getOne(member.get("account_username").toString()));
        card.setMembers(members);
        return cardRepository.saveAndFlush(card);
    }

    @PostMapping("delete-member")
    public Card deleteMember (@RequestBody Map<String,Object> member){
        Card card=cardRepository.getOne(Long.parseLong(member.get("card_id").toString()));
        Set<Account> members=card.getMembers();
        if(members==null){
            members=new HashSet<Account>();
        }
        members.removeIf(account->account.getUsername().equals(member.get("account_username").toString()));
        card.setMembers(members);
        return cardRepository.saveAndFlush(card);
        
    }

    @RequestMapping(method = RequestMethod.PUT,value = "{cardId}")
    public Card updateCard(@PathVariable Long cardId,@RequestBody Card card) {
        if(!cardRepository.existsById(cardId)){
            throw new RuntimeException("CardId " + cardId + " not found");
        }
        else {
            Card oldCard = cardRepository.getOne(cardId);
            BeanUtils.copyProperties(card, oldCard, "id", "dateCreated");
            oldCard.setList(listRepository.getOne(card.getList().getId()));
            return cardRepository.saveAndFlush(oldCard);
        }
    }    

    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    public void deleteCardById(@PathVariable Long id){
        if(!cardRepository.existsById(id)){
            throw new RuntimeException("CardId " + id + " not found");
        }
        else {
            cardRepository.deleteById(id);
        }
    }

    @GetMapping("/title/{title}")
    public List<Card> getCardByTitle(@PathVariable String title){
        return cardRepository.findByTitleContaining(title);
    }

    @GetMapping("/add-label")
    public Card addLabel(@RequestBody Map<String,Object> label){
        Card card=cardRepository.getOne(Long.parseLong(label.get("card_id").toString()));
        Set<Label> labels=card.getLabels();
        if(labels==null)
            labels=new HashSet<Label>();
        labels.add(labelRepository.getOne(Long.parseLong(label.get("label_id").toString())));
        card.setLabels(labels);
        return cardRepository.saveAndFlush(card);
    }

}
