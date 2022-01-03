package com.idus.web.product.product;

import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/read")
    public void read(int idx, Model model) {
//       read 메소드 호출 시 웹브라우저가 컨트롤러에게 idx, 비어있는 model을 전달.


        ProductDTO productDTO = productService.readService(idx);
//        idx 값을 담은 read 서비스의 read 함수 값을, ProductDTO 타입의 productDTO에 저장.
//        ProductDTO 타입의 productDTO에, productService의 readService에 idx을 전달
//        idx을 전달받은 readService 메소드의 반환값을 ProductDTO 타입의 productDTO에 저장.

        System.out.println(productDTO.toString());

        model.addAttribute("dto", productDTO);
//        productDTO를 dto라는 이름으로 model에 (addAttribute)추가함.
    }



    @GetMapping("/write")
    public void write_get() {

    }

    @PostMapping("/write")
    public String write_post(ProductDTO productDTO){
        System.out.println(productDTO.toString());

        productService.saveService(productDTO);

        return "redirect:/";
    }
}
