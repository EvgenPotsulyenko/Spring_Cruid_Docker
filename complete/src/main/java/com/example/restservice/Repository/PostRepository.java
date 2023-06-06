package com.example.restservice.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.restservice.Models.Post;

public interface PostRepository extends CrudRepository<Post, Long> 
{
    
}
