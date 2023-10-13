package com.carrot.easy.service;

import com.carrot.easy.domain.Item;
import com.carrot.easy.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public Long saveItem(Item item){
        return itemRepository.save(item);
    }

    public Item findItem(Long itemId){
        return itemRepository.findById(itemId);
    }
}
