package com.bujikun.fsd.capstone.eHealthcare.repository;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Product;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends BaseRepository<Product, UUID>{
    @Query("""
  SELECT `products`.`id` AS `id`, 
  `products`.`name` AS `name`, 
  `products`.`price` AS `price`, 
  `products`.`img_url` AS `img_url`, 
  `products`.`created_by` AS `created_by`, 
  `products`.`created_on` AS `created_on`,
   `products`.`updated_on` AS `updated_on`, 
  `products`.`description` AS `description`, 
  `products`.`last_modified_by` AS `last_modified_by`,
  c.name AS `category_name`
   FROM `products`
   JOIN products_categories pc on products.id = pc.fk_product_id
   JOIN categories c on pc.fk_category_id = c.id
""")
    List<ProductDTO> findAllWithCategory();
    @Query("""
  SELECT `products`.`id` AS `id`, 
  `products`.`name` AS `name`, 
  `products`.`price` AS `price`, 
  `products`.`img_url` AS `img_url`, 
  `products`.`created_by` AS `created_by`, 
  `products`.`created_on` AS `created_on`,
   `products`.`updated_on` AS `updated_on`, 
  `products`.`description` AS `description`, 
  `products`.`last_modified_by` AS `last_modified_by`,
  c.name AS `category_name`
   FROM `products`
   JOIN products_categories pc on products.id = pc.fk_product_id
   JOIN categories c on pc.fk_category_id = c.id
   WHERE `products`.id = :id
""")
    Optional<ProductDTO> findOneById(@Param("id") UUID id);
}
