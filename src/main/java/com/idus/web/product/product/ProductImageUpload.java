package com.idus.web.product.product;

import lombok.*;

import javax.persistence.*;

@Table(name = "productimage")
@Entity
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "product")

public class ProductImageUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idx;
    private String imgName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Product product;
}
