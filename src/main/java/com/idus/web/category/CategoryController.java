package com.idus.web.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/create")
    public void create(CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);

    }

    @GetMapping("/test")
    public void main() {

        //when
        CategoryDTO categoryRoot = categoryService.createRootCategory();

        System.out.println(categoryRoot.toString());
    }

}
