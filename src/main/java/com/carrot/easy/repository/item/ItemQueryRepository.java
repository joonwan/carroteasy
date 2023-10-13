package com.carrot.easy.repository.item;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemQueryRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Item> findItemsByLocate(Address address) {
        return em.createQuery("select i from Item i where i.address = :address")
                .setParameter("address",address)
                .getResultList();
    }


}
