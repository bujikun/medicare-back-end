package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.ProductDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Product;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.product.ProductNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.repository.CategoryRepository;
import com.bujikun.fsd.capstone.eHealthcare.repository.ProductRepository;
import com.bujikun.fsd.capstone.eHealthcare.repository.SellerRepository;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService implements IBaseService<Product, UUID> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;
    private final DateUtil dateUtil;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public List<ProductDTO> findAllAvailable() {
        return productRepository.findAllAvailable();
    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        return productRepository.findById(uuid);

    }

    public Optional<ProductDTO> findOneById(UUID uuid) {
        var op =  productRepository.findOneById(uuid);
return op;
    }

    public ProductDTO create(ProductDTO productDTO) {
        var category = categoryRepository.findById(productDTO.getCategoryId()).get();
        var seller = sellerRepository.findById(productDTO.getSellerId()).get();
        var product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .productSellers(new HashSet<>())
                .productCategories(new HashSet<>())
                .createdOn(dateUtil.now())
                .deleted(false)
                .build();
        product.linkSeller(seller);
        product.linkCategory(category);
         productRepository.save(product);
         productDTO.setId(product.getId());
        return productDTO;
    }

    public boolean updateProduct(ProductDTO productDTO) {
        var category = categoryRepository.findById(productDTO.getCategoryId()).get();
        var seller = sellerRepository.findById(productDTO.getSellerId()).get();
        var product = findById(productDTO.getId())
                .orElseThrow(()->new ProductNotFoundException("Product Not Found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setUpdatedOn(dateUtil.now());
        if(!product.isLinkedToSeller(seller)){
            product.linkSeller(seller);
        }
        if(!product.isLinkedToCategory(category)){
            product.linkCategory(category);
        }
        productRepository.save(product);
        return true;
    }



    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean update(Product product) {
        var p = productRepository.save(product);
        return Optional.ofNullable(p.getId()).isPresent();
    }
    public boolean updateProductImage(ProductDTO productDTO) {
        var product = findById(productDTO.getId())
                .orElseThrow(()->new ProductNotFoundException("Product Not Found"));
        product.setImageUrl(productDTO.getImageUrl());
        product.setUpdatedOn(dateUtil.now());
         productRepository.save(product);
        return true;
    }

    @Override
    public void deleteById(UUID uuid) {
        productRepository.deleteById(uuid);
    }

    @Override
    public Integer getCount() {
        return productRepository.getCount();
    }

    public List<ProductDTO> findAllWithCategory(){
        return productRepository.findAllWithCategory();
    }
    public List<ProductDTO> search(String query){
        return productRepository.search(query);
    }

}
