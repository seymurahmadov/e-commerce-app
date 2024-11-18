package org.ecommerce.product.repository;

import org.ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    List<Product> findAllByIdInOrderById(List<Integer> productsIds);


    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
