package com.riyanJ.facebookcloneservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.riyanJ.facebookcloneservice.entity.PostEntity;
import com.riyanJ.facebookcloneservice.model.Post;
import com.riyanJ.facebookcloneservice.repository.PostEntityRepository;

@Service
public class PostServiceImpl implements PostService{

    private PostEntityRepository postEntityRepository;

    public PostServiceImpl(PostEntityRepository postEntityRepository) {
        this.postEntityRepository = postEntityRepository;
    }

    @Override
    public Post addPost(Post post) {

        try {
            PostEntity postEntity = new PostEntity();
            BeanUtils.copyProperties(post, postEntity);

            if (post.getFile()!=null && !post.getFile().equalsIgnoreCase("null")) {
                postEntity.setImage(post.getFile());
            }

            else 
                postEntity.setImage(null);

            postEntity = postEntityRepository.save(postEntity);
            post.setId(postEntity.getId());
            post.setFile(null);
            post.setImage(postEntity.getImage());

        } catch (Exception e ){
            throw new RuntimeException ("Could not save post: " + e.getMessage(), e);
        }   
        return post;
    }

    @Override
    public List<Post> getPost() {
        List<PostEntity> postEntities
                = postEntityRepository.findAll();

        List<Post> posts = new ArrayList<>();
        posts = postEntities.stream()
                .map((postEntity) ->
                    Post.builder()
                            .id(postEntity.getId())
                            .timeStamp(postEntity.getTimeStamp())
                            .email(postEntity.getEmail())
                            .name(postEntity.getName())
                            .post(postEntity.getPost())
                            .image(postEntity.getImage())
                            .profilePic(postEntity.getProfilePic())
                            .build()
                ).collect(Collectors.toList());
        return posts;
    }

}
