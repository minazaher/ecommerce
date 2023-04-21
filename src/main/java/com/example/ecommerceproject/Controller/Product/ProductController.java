package com.example.ecommerceproject.Controller.Product;


import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/shop")
public class ProductController {
    private final ProductService productService;
    private final ProductRestController productRestController;
    List<Product> products = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    List<String> brands = new ArrayList<>();

    @RequestMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/products/{category}/{id}")
    public String getProductCategory(@PathVariable Long id, @PathVariable String category, Model model) {
        Product product = productRestController.getProductById(id);
        category = product.getCategory();
        model.addAttribute("product", product);
        return "single-product";
    }

    @GetMapping("/products/{page}")
    public String getShop(@PathVariable int page, Model model) {
        initializeData();
        products = productRestController.getProductsPage(page);
        sendAttributes(model);
        return "category";
    }

    @GetMapping("/products")
    public String getProductsByCategory(@RequestParam(value = "category", required = false, defaultValue = "all") String category,
                                        Model model) {
        initializeData();
        if (category.equals("all"))
            products = productService.getAllProducts();
        else
            products = productService.getProductsByCategory(category);

        sendAttributes(model);
        return "category";
    }

//    @GetMapping("/products/ordered_DESC")
//    public String getProductsOrdered(Model model) {
//        initializeData();
//        products = productRestController.getProductsOrderedDESC();
//        sendAttributes(model);
//        return "category";
//    }

    @GetMapping("/product")
    public String getProductsByBrand(@RequestParam(value = "brand", required = false) String brand
            , Model model) {
        initializeData();
        products = productService.getProductsByBrand(brand);
        sendAttributes(model);
        return "category";
    }

    private void initializeData(){
        categories = productService.getCategories();
        brands = productService.getBrands();
    }

    private void sendAttributes(Model model){
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

    }


}

