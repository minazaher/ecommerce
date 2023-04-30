package com.example.ecommerceproject.Controller.Product;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Model.Role;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ProductRestController {
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

        @RequestMapping("/save")
        public void saveAllProductsToDatabase() {
            productService.saveAllProductsToDatabase();
        }


        @PostMapping("/Admin/addProduct")
            public ResponseEntity<String> addProduct(@RequestBody Product product){
            productService.saveProduct(product);
            return ResponseEntity.ok("Product Added!");
        }


}
