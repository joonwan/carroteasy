package com.carrot.easy.service;

import com.carrot.easy.domain.LifePost;
import com.carrot.easy.repository.LifePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LifePostService {

    private final LifePostRepository lifePostRepository;

    // post 등록 로직
    @Transactional(readOnly = false)
    public Long register(LifePost lifePost){
        return lifePostRepository.save(lifePost);
    }

    public LifePost find(Long postId){
        return lifePostRepository.findById(postId);
    }

}
