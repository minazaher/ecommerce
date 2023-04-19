package com.example.ecommerceproject.Service;

import com.example.ecommerceproject.DTO.ProductExternalResponse;
import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    @Cacheable("products_cache")
    public List<ProductExternalResponse> getProducts() {
        String apiUrl = "https://dummyjson.com/products/";
        List<ProductExternalResponse> products = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            ProductExternalResponse Response = restTemplate.getForObject(apiUrl+i, ProductExternalResponse.class);
            products.add(Response);
        }
        return products;
    }

    public void saveAllProductsToDatabase(){
        for (ProductExternalResponse p: getProducts()) {
            productRepository.saveAndFlush(mapToProduct(p));
        }
    }

    private Product mapToProduct(ProductExternalResponse product) {
        return Product.builder()
                .id(product.getId())
                .price(product.getPrice())
                .stock(product.getStock())
                .brand(product.getBrand())
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .title(product.getTitle())
                .category(product.getCategory()).build();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<String> getCategories() {
        return productRepository.findCategories();
    }

    public List<String> getBrands() {
        return productRepository.findBrands();
    }

    public Product getProductsById(Long id) {
        return productRepository.findProductById(id);
    }

    public List<Product> getProductsPage(int page) {
        return productRepository.findProductsByPage(PageRequest.of(page+1, 20));
    }

    public List<Product> getProductsOrderedByPriceDESC() {
        return productRepository.OrderByPriceDesc();
    }}
