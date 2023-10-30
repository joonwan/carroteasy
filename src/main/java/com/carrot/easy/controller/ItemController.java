package com.carrot.easy.controller;

import com.carrot.easy.domain.Item;
import com.carrot.easy.domain.Member;
import com.carrot.easy.file.FileStore;
import com.carrot.easy.service.ItemService;
import com.carrot.easy.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:19006")
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final FileStore fileStore;

    @GetMapping
    public List<ItemsDto> items() {
        List<Item> items = itemService.findAll();
        return items.stream().map(
                        (i) -> new ItemsDto(i.getId(), null, i.getItemName(), i.getAddress().getGu(), i.getAddress().getDong(), i.getCreateDate(), i.getPrice(), i.getInterestCount(), i.getImage().getStoreFileName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    public String createItem(@ModelAttribute ItemRegisterDtoWithImg param) throws IOException {
        log.info("param = {}", param);

        Member member = memberService.findMember(param.getMemberId());
        UploadFile imageFile = fileStore.storeFile(param.getImage());

        Item item = new Item(param.getItemName(), member, param.getPrice(), param.getContent(), 0, member.getAddress(), LocalDateTime.now(), imageFile);
        imageFile.setItem(item);
        itemService.saveItem(item);
        return "ok";
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable Long itemId, @RequestParam Long memberId) {
        Item item = itemService.findItem(itemId);
        Member seller = item.getSeller();
        boolean isLike = itemService.isLike(itemId, memberId);
        return new ItemDto(item.getSeller().getName(), item.getAddress().getGu(), item.getAddress().getDong(), seller.getMannerTemperature(), item.getItemName(), item.getCreateDate(), item.getContent(), item.getImage().getStoreFileName(), item.getPrice(), isLike);
    }

    @PutMapping("/{itemId}")
    public String changeLike(@PathVariable Long itemId, @RequestParam Long memberId) {

        itemService.changeInterestItem(itemId, memberId);
        Member member = memberService.findMember(memberId);
        return "ok";

    }


    @GetMapping("/image/{storeFileName}")
    public Resource downloadImage(@PathVariable String storeFileName) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(storeFileName));
    }


    @Data
    static class ItemsDto {
        private Long itemId;
        private Long memberId;
        private String itemName;
        private String gu;
        private String dong;
        private LocalDateTime createTime;
        private int price;
        private int interestCount;
        private String uri;


        public ItemsDto(Long itemId, Long memberId, String itemName, String gu, String dong, LocalDateTime createTime, int price, int interestCount, String uri) {
            this.itemId = itemId;
            this.memberId = memberId;
            this.itemName = itemName;
            this.gu = gu;
            this.dong = dong;
            this.createTime = createTime;
            this.price = price;
            this.interestCount = interestCount;
            this.uri = uri;

        }
    }

    @Data
    static class ItemRegisterDtoWithImg {

        private Long memberId;
        private String itemName;
        private int price;
        private String content;
        private MultipartFile image;

    }

    @Data
    static class ItemRegisterDto {

        private Long memberId;
        private String itemName;
        private int price;
        private String content;

    }

    @Data
    static class ItemDto {

        private String sellerName;
        private Double mannerTemperature;
        private String gu;
        private String dong;
        private String itemName;
        private LocalDateTime createDate;
        private String content;
        private String uri;
        private int price;
        private boolean isLike;

        public ItemDto(String sellerName, String gu, String dong, Double mannerTemperature, String itemName, LocalDateTime createDate, String content, String uri, int price, boolean isLike) {
            this.sellerName = sellerName;
            this.gu = gu;
            this.dong = dong;
            this.mannerTemperature = mannerTemperature;
            this.itemName = itemName;
            this.createDate = createDate;
            this.content = content;
            this.uri = uri;
            this.price = price;
            this.isLike = isLike;
        }
    }


}
