package com.example.gccoffee.controller;

import com.example.gccoffee.model.Product;
import com.example.gccoffee.service.DefaultProductService;
import com.example.gccoffee.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping("/products")
    public String productsPage(Model model){
        var products = productService.getAllProducts();
        logger.info(products.toString()+"list좀 보자");
        model.addAttribute("products",products);
        return "product-list";
    }

    @GetMapping("/new-product")
    public String newProductPage(){
        return "new-product";
    }

    @PostMapping("/products")
    public String newProduct(CreateProductRequest createProductRequest){
        productService.createProduct(createProductRequest.getName(),
                createProductRequest.getCategory(),
                createProductRequest.getPrice(),
                createProductRequest.getDescription());
        return "redirect:/products";
    }

}
