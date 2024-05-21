package com.riyanJ.facebookcloneservice.service;

import org.springframework.stereotype.Service;

import com.riyanJ.facebookcloneservice.repository.PostEntityRepository;

@Service
public class PostServiceImpl implements PostService{

    private PostEntityRepository postEntityRepository;

    public PostServiceImpl(PostEntityRepository postEntityRepository) {
        this.postEntityRepository = postEntityRepository;
    }

}
