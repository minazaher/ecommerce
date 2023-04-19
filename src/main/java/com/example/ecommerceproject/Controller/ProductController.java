package com.example.ecommerceproject.Controller;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/page/{page}")
    public List<Product> getProductsPage(@PathVariable int page) {
        return productService.getProductsPage(page);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductsById(id);
    }

    @RequestMapping("/admin/save")
    public void saveAllProductsToDatabase() {
        productService.saveAllProductsToDatabase();
    }

}
