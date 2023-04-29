package com.example.ecommerceproject.Controller.Product;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Model.Role;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
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


        @PostMapping("/add")
            public String addProduct(HttpSession session, Product product){
            User user = (User) session.getAttribute("user");
            if (user.getRole() == Role.ADMIN){
                productService.saveProduct(product);
                return product.toString();
            }
            return "You Don't Have the authority to Add product";
        }


}
