package com.tranquyet.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	public static final String SEARCH_PRODUCT = "select * from product as p where "
			+ "p.title like %:word% or content like %:word% " + "order by p.id";

	public static final String CHANGE_CATEGORY = "UPDATE product as p SET p.id_category = 6 WHERE p.id_category = :idCategory";

	public static final String CHANGE_ID_CATEGORY = "select p.id_category from product as p where p.id_category =:idCategory";

	ProductEntity findOneById(Long id);

	@Query(value = SEARCH_PRODUCT, nativeQuery = true)
	@Transactional
	Page<ProductEntity> searchProduct(Pageable page, @Param("word") String word);

	@Modifying
	@Transactional
	@Query(value = CHANGE_CATEGORY, nativeQuery = true)
	public void editCategoryProduct(@Param("idCategory") Long idCategory);
	
	@Transactional
	@Query(value=CHANGE_ID_CATEGORY, nativeQuery=true)
	public Long getIdCategory(@Param("idCategory") Long idCategory);
	
	@Transactional
	@Query(value="select * from product as p inner join category as c on p.id_category = c.id where c.name like :categoryName", nativeQuery=true)
	public List<ProductEntity> getProductByCategory(@Param("categoryName")String categoryName);
	
	@Transactional
	@Query(value="select p.title, p.content from product as p inner join category as c on p.id_category = c.id where c.name like :categoryName", nativeQuery=true)
	public List<Object[]> getProductCategory(@Param("categoryName")String categoryName);

}
