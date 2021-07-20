package com.tranquyet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranquyet.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	
	CategoryEntity findOneById(Long id);
	
	CategoryEntity findOneByName(String name);
}
