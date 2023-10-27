package com.carrot.easy.controller;

import com.carrot.easy.domain.Item;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Getter
public class UploadFile {

    @Id @GeneratedValue
    private Long id;
    private String uploadFileName;
    private String storeFileName;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    public UploadFile() {
    }

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public void setItem(Item item){
        this.item = item;
    }
}
