package com.carrot.easy.service;

import com.carrot.easy.domain.InterestItem;
import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import com.carrot.easy.repository.InterestItemRepository;
import com.carrot.easy.repository.ItemRepository;
import com.carrot.easy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final InterestItemRepository interestItemRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Long addInterestItem(Long memberId, Long itemId) {
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);
        item.addInterestCount();
        InterestItem interestItem = new InterestItem(member, item);
        interestItem.addThisToMember(member);
        interestItemRepository.save(interestItem);

        return item.getId();
    }

    public boolean isLike(Long itemId, Long memberId) {
        Member member = memberRepository.findById(memberId);
        List<InterestItem> interestItems = member.getInterestItems();
        Item item = itemRepository.findById(itemId);

        for (InterestItem interestItem : interestItems) {
            if (interestItem.getItem().getId().equals(itemId)) {
                return true;
            }
        }

        return false;
    }

    @Transactional(readOnly = false)
    public void changeInterestItem(Long itemId, Long memberId) {
        boolean like = isLike(itemId, memberId);
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        if (like) {
            InterestItem interestItem = interestItemRepository.findById(member, item);
            member.getInterestItems().remove(interestItem);
            interestItemRepository.remove(interestItem);
            item.minusInterestCount();
        } else {
            InterestItem interestItem = new InterestItem(member, item);
            interestItem.addThisToMember(member);
            interestItemRepository.save(interestItem);
            item.addInterestCount();
        }

    }


}
