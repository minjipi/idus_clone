package com.idus.web.product.product;

import com.idus.web.product.product.dto.ProductDTO;
import com.idus.web.product.product.dto.ProductImageUploadDTO;
import com.idus.web.product.product.dto.UploadResultDTO;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/update")
    public String productupdate_get(int idx, Model model) {
        ProductDTO productDTO = productService.readService(idx);
        System.out.println(productDTO.toString());
        model.addAttribute("dto", productDTO);

//        model.addAttribute("dto", productService.readService(idx));

        return "/product/update";
    }

    @PostMapping("/update")
    public String productupdate_post(ProductDTO productDTO) {
//        productService.updateService(productDTO);

        return "redirect:/";
    }



    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, String size) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");


            File file = new File("/Users/minz/Desktop/upload" + File.separator + srcFileName);

            if (size != null && size.equals("1")) {
                file = new File(file.getParent(), file.getName().substring(2));
            }

            HttpHeaders header = new HttpHeaders();

            //MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Value("/Users/minz/Desktop/upload")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile: uploadFiles) {

            if(uploadFile.getContentType().startsWith("image") == false) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            //저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uploadPath + File.separator + fileName;
            Path savePath = Paths.get(saveName);

            try {
                //원본 파일 저장
                uploadFile.transferTo(savePath);

                resultDTOList.add(new UploadResultDTO(fileName));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }//end for
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }


    @GetMapping("/write")
    public void write_get() {

    }

    @PostMapping("/write")
    public String write_post(ProductDTO productDTO, MultipartFile productimage) {

        productDTO.getProductImageUploadDTOList().add(new ProductImageUploadDTO(productimage.getOriginalFilename()));

        try {
            System.out.println(productimage.getOriginalFilename());
            productimage.transferTo(Paths.get("/Users/minz/Desktop/upload/" + productimage.getOriginalFilename()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(productDTO.toString());
        productService.saveService(productDTO);
        return "redirect:/";
    }




}
