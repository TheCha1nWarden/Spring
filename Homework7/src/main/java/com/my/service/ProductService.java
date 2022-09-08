package com.my.service;

import com.my.model.dto.ProductDto;
import com.my.model.mapper.ProductDtoMapper;
import com.my.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper mapper;

    public Page<ProductDto> findAllByFilter(String titleFilter, int page, int size) {
        titleFilter = titleFilter == null || titleFilter.isBlank() ? null : "%" + titleFilter.trim() + "%";
        return productRepository.productsByTitle(titleFilter, PageRequest.of(page, size)).map(mapper::map);
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void saveProduct(ProductDto productDto) {
        productRepository.save(mapper.map(productDto));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
