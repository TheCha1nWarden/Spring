package com.my.homework4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.getProducts());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("product", productRepository.getProductById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product(""));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productRepository.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.saveProduct(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productRepository.saveProduct(product);
        return "redirect:/product";
    }
}
