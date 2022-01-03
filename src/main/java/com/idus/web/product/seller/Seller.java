package com.idus.web.product.seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "seller")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx;
    String seller_name;
    int keep;
    int follower;
    int sponsor;
    String intro;
    String profile;

}
