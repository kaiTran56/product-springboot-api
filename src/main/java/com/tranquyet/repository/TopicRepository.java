package com.tranquyet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranquyet.entity.TopicEntity;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
	TopicEntity findOneById(Long id);
	
	TopicEntity findOneByName(String name);
}
