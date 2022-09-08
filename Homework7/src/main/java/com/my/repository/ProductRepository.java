package com.my.repository;

import com.my.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
            SELECT * FROM products p
            WHERE :titleFilter IS NULL OR p.title LIKE :titleFilter
            """,
            countQuery = """
            SELECT count(*) FROM products p
            WHERE :titleFilter IS NULL OR p.title LIKE :titleFilter
            """,
            nativeQuery = true)
    Page<Product> productsByTitle(String titleFilter, Pageable pageable);

}
