package com.idus.web.product.option;

import com.idus.web.product.optionSelect.OptionSelect;
import com.idus.web.product.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "options")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Option {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx;
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @OneToMany(mappedBy = "option", cascade = CascadeType.REMOVE)
    private List<OptionSelect> optionSelectList = new ArrayList<>();
}
