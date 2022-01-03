package com.idus.web.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx;
    String name;
    int pidx;

    public Category(String name, int pidx){
        this.name=name;
        this.pidx=pidx;
    }

}
