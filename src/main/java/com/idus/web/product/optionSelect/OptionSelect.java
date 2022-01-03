package com.idus.web.product.optionSelect;


import com.idus.web.product.option.Option;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "optionSelect")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "option")
public class OptionSelect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx;
    String title;
    int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;
}
