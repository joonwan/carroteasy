package com.carrot.easy.repository.item;

import com.carrot.easy.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;


    public Long save(Item item){
        em.persist(item);
        return item.getId();
    }

    public Item findById(Long itemId){
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }
}
