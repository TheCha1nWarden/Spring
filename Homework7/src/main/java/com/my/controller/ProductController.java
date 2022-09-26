package com.my.controller;

import com.my.model.dto.ProductDto;
import com.my.repository.ProductRepository;
import com.my.exceptions.EntityNotFoundException;
import com.my.model.Product;
import com.my.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String listPage(
            @RequestParam(required = false) String titleFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(5);
        model.addAttribute("products", productService.findAllByFilter(titleFilter, pageValue, sizeValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productService.saveProduct(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") ProductDto product) {
        productService.saveProduct(product);
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }
}
