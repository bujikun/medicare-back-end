package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "products")
@Slf4j
public class Product extends BaseEntity {
    @Column("name")
    private String name;
    @Column("price")
    private BigDecimal price;
    @Column("img_url")
    private String imageUrl;
    private String description;
    @MappedCollection(idColumn = "fk_product_id", keyColumn = "fk_category_id")
    private Set<ProductCategory> productCategories;
    @MappedCollection(idColumn = "fk_product_id", keyColumn = "fk_seller_id")
    private Set<ProductSeller> productSellers;

    {
        productCategories = new HashSet<>();
        productSellers = new HashSet<>();
    }

    public void linkCategory(Category category) {
        var pc = new ProductCategory();
        pc.setCategoryId(AggregateReference.to(category.getId()));
        pc.setCreatedOn(DateUtil.getNow());
        if(productCategories.contains(pc)){
            return;
        }
        productCategories.add(pc);
    }

    public void linkCategories(Set<Category> categories) {
        categories.stream()
                .map(category -> {
            var pc = new ProductCategory();
            pc.setCategoryId(AggregateReference.to(category.getId()));
            pc.setCreatedOn(DateUtil.getNow());
            return pc;
        })
                .forEach(productCategories::add);
    }
    public void linkSeller(Seller seller) {
        var pc = new ProductSeller();
        pc.setSellerId(AggregateReference.to(seller.getId()));
        pc.setCreatedOn(DateUtil.getNow());
        if(productSellers.contains(pc)){

            return;
        }
        productSellers.add(pc);
    }

    public void linkSellers(Set<Seller> sellers) {
        sellers.stream()
                .map(category -> {
                    var pc = new ProductSeller();
                    pc.setSellerId(AggregateReference.to(category.getId()));
                    pc.setCreatedOn(DateUtil.getNow());
                    return pc;
                })
                .forEach(productSellers::add);
    }

    public boolean isLinkedToSeller(Seller seller){
       return productSellers.stream()
                .map(ps->ps.getSellerId().getId())
                .anyMatch(id->id.equals(seller.getId()));
    }

    public boolean isLinkedToCategory(Category category){
        return productCategories.stream()
                .map(pc->pc.getCategoryId().getId())
                .anyMatch(id->id.equals(category.getId()));
    }
}
