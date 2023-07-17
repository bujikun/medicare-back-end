package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "products")
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

    {
        productCategories = new HashSet<>();
    }

    public void linkCategory(Category category) {
        var pc = new ProductCategory();
        pc.setCategoryId(AggregateReference.to(category.getId()));
        pc.setCreatedOn(DateUtil.getNow());
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
//    @Transient
//    private Function<Category,ProductCategory> categoryToProductCategory = (Category category) -> {
//        var pc = new ProductCategory();
//        pc.setCategoryId(AggregateReference.to(category.getId()));
//        pc.setCreatedOn(DateUtil.getNow());
//        return pc;
//    };
}
