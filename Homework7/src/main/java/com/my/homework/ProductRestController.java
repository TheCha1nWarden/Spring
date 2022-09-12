package com.my.homework;

import com.my.exceptions.EntityNotFoundException;
import com.my.model.dto.ProductDto;
import com.my.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> listPage(
            @RequestParam(required = false) String titleFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(5);
        Page<ProductDto> allByFilter = productService.findAllByFilter(titleFilter, pageValue, sizeValue);
        return allByFilter.get().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable Long id, Model model) {
        return productService.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @PostMapping
    public ProductDto saveProduct(ProductDto product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        productService.saveProduct(product);
        return product;
    }

    @PostMapping("/update")
    public ProductDto updateProduct(ProductDto product) {
        productService.saveProduct(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
