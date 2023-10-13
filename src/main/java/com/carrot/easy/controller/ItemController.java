package com.carrot.easy.controller;

import com.carrot.easy.domain.Address;
import com.carrot.easy.domain.Item;
import com.carrot.easy.repository.item.ItemQueryRepository;
import com.carrot.easy.repository.item.ItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemQueryRepository itemQueryRepository;

    @GetMapping
    public List<ItemDTO> items() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(ItemDTO::new).collect(Collectors.toList());
    }


    @Data
    static class ItemDTO {
        private Long itemId;
        private String itemName;
        private Address address;

        private int price;
        private int interestCount;

        public ItemDTO(Item item) {
            this.itemId = item.getId();
            this.itemName = item.getIteName();
            this.address = item.getAddress();
            this.price = item.getPrice();
            this.interestCount = item.getInterestCount();
        }
    }
}
