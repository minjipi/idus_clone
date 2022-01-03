package com.idus.web.product.product;

import com.idus.web.product.seller.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx;
    String productName;
    int price;
    int salePrice;
//    int like;
    int quantity;
    String productInfo;
    String tags;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

}
