package com.idus.web.main;

import com.idus.web.product.product.ProductService;
import com.idus.web.product.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String main(Model model){

        Set<ProductDTO> productDTOList = productService.listService();
        model.addAttribute("productDTOList", productDTOList);
        System.out.println("============== productDTOList: " + productDTOList);

        return "main.html";
    }

    @GetMapping("/test")
    public String test(){
        return "test.html";
    }
}
