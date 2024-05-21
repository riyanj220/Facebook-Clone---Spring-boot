package com.riyanJ.facebookcloneservice.service;

import java.util.List;

import com.riyanJ.facebookcloneservice.model.Post;

public interface PostService {
    Post addPost(Post post);

    List <Post> getPost();
}
