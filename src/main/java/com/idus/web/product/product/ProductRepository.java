package com.idus.web.product.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findByIdx(int idx);

    @Query("SELECT p, pi FROM Product p LEFT OUTER JOIN ProductImageUpload pi on pi.product = p")
    List<Object[]> getProductWithImage();

    @Query("SELECT p, o, os, pi FROM Product p LEFT OUTER JOIN Option o on o.product = p LEFT OUTER JOIN OptionSelect os ON os.option = o LEFT OUTER JOIN ProductImageUpload pi on pi.product = p WHERE p.idx = :idx")
    List<Object[]> getProductWithAll(int idx);

}
