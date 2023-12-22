package com.withdog.post.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.withdog.post.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer>  {

	public Slice<PostEntity> findAllByOrderByIdDesc(Pageable pageable); 
}
