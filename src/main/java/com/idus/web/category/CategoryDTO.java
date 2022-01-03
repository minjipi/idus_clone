package com.idus.web.category;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    int idx;
    String name;

    int pidx;
    List<CategoryDTO> subCategorylist;

    public CategoryDTO(int idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    public CategoryDTO(int idx, String name, int pidx) {
        this.idx = idx;
        this.name = name;
        this.pidx = pidx;
    }
}
