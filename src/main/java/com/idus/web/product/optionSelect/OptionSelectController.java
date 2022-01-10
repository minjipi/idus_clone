package com.idus.web.product.optionSelect;

import com.idus.web.product.product.ProductService;
import com.idus.web.product.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/optionSelect")
public class OptionSelectController {

    @Autowired
    OptionSelectService optionSelectService;

    @PostMapping("/delete")
    public String delete(int idx) {
        System.out.println("test" + idx);
        optionSelectService.deleteService(idx);
        return "ok";
    }
}
