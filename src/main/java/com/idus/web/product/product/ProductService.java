package com.idus.web.product.product;

import com.idus.web.product.option.Option;
import com.idus.web.product.option.OptionDTO;
import com.idus.web.product.option.OptionRepository;
import com.idus.web.product.optionSelect.OptionSelect;
import com.idus.web.product.optionSelect.OptionSelectDTO;
import com.idus.web.product.optionSelect.OptionSelectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    OptionSelectRepository optionSelectRepository;

    public void saveService(ProductDTO productDTO) {
        Product product = Product.builder()
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .salePrice(productDTO.getSalePrice())
                .quantity(productDTO.getQuantity())
                .productInfo(productDTO.getProductInfo())
                .tags(productDTO.getTags())
                .build();
        productRepository.save(product);

        List<OptionDTO> optionDTOList = productDTO.getOptionDTOList();
        if (optionDTOList.size() > 0) {
            for (int i = 0; i < optionDTOList.size(); i++) {
                OptionDTO optionDTO = optionDTOList.get(i);

                Option option = Option.builder()
                        .title(optionDTO.getTitle())
                        .product(product)
                        .build();

                optionRepository.save(option);

                List<OptionSelectDTO> optionSelectDTOList = optionDTO.getOptionSelectDTOList();
                if (optionSelectDTOList.size() > 0) {
                    for (int j = 0; j < optionSelectDTOList.size(); j++) {
                        OptionSelectDTO optionSelectDTO = optionSelectDTOList.get(j);

                        OptionSelect optionSelect = OptionSelect.builder()
                                .title(optionSelectDTO.getTitle())
                                .option(option)
                                .price(optionSelectDTO.getPrice())
                                .build();
                        optionSelectRepository.save(optionSelect);
                    }
                }
            }
        }
    }

    public ProductDTO readService(int idx) {
        List<Object[]> result = productRepository.getProductWithAll(idx);
//      레포지터리의 getProductWithAll 메소드에 idx값을 전달하고, 이를 리스트 타입의 result에 저장.
//      사용자가 전달한 idx와 일치하는 product의 idx와 관련된  product, option, optionSelect 값이 여러개가 담겨있음.
//         []    0       1            2
//       + - - - - - - - - - - - - - - - - - - -
// get() |
//  0    | product1, option1-1, optionselect1-1-1       한 줄 한 줄은 Object타입의 배열( [] 이게 배열  )이다
//  1    | product1, option1-1, optionselect1-1-2
//  2    | product1, option1-1, optionselect1-1-3
//  3    | product1, option1-2, optionselect1-2-1
//  4    | product1, option1-2, optionselect1-2-2


        Product product = (Product) result.get(0)[0];    // product1, option1-1, optionselect1-1-1
//      Object 타입의 배열 이루어진 리스트 result의, 첫번째(0) 리스트의 첫번째 배열에 저장된 값을 Product 타입으로 형변환 해주고
//      Product 타입 product의 라는 변수에 저장한다.

        Set<Option> optionSet = new HashSet<>();            //비어있는 Option엔티티 세트를 생성

        List<OptionSelect> optionSelectList = new ArrayList<>(); //비어있는 OptionSelect엔티티 리스트를 생성

        if (result.get(0)[1] != null && result.get(0)[2] != null) {
//         result의 첫번째 리스트에 있는 두번째 값(option의 첫번째)이 비어있거나, 첫번째 리스트에 있는 세번째 값(option select의 첫번째)이 비어있다면


//            for(int i=0; i< result.size(); i++) {
//                Object[] arr = result.get(i);
//            }


//            result는 다음과 같다

//         []    0       1            2
//       + - - - - - - - - - - - - - - - - - - -
// get() |
//  0    | product1, option1-1, optionselect1-1-1       한 줄 한 줄은 Object타입의 배열( [] 이게 배열  )이다
//  1    | product1, option1-1, optionselect1-1-2
//  2    | product1, option1-1, optionselect1-1-3
//  3    | product1, option1-2, optionselect1-2-1
//  4    | product1, option1-2, optionselect1-2-2


//         forEach반복문은 result에 저장된 값을 하나씩 꺼내 arr 이라는 변수(result.get(i))에 담는데,
//         첫번째 반복문에서 arr은 product1, option1-1, optionselect1-1-1
//         두번째 반복문에서 arr은 product1, option1-1, optionselect1-1-2
//         세번째 반복문에서 arr은 product1, option1-1, optionselect1-1-3
//         네번째 반복문에서 arr은 product1, option1-2, optionselect1-2-2
            result.forEach(arr -> {
                Option option = (Option) arr[1];
                optionSet.add(option);
//          배열 arr의 두번째에 Option 타입으로 형변환을 한 Option 타입의 option을 저장하고
//          배열 arr의 두번째의 값을 Option 타입으로 형변환하여 Option 타입의 option이라는 변수에 저장한다.
//          optionSet에 그 변수를 추가한다.

                OptionSelect optionSelect = (OptionSelect) arr[2];
                optionSelectList.add(optionSelect);
//          배열 arr의 세번째 값을 Option 타입으로 형변환하여 Option 타입의 option이라는 변수에 저장한다
//          optionSet에 그 변수를 추가한다.
            });
        }

        List<OptionDTO> optionDTOList = new ArrayList<>(); // 비어있는 optionDTOList를 생성

//      optionSet에 저장된 값을 (배열 arr의 두번째 값)
//      하나씩 꺼내서 option이라는 변수에 담는데,
        optionSet.forEach(option -> {

//      option 엔티티에서 가져온 idx, title를 OptionDTO 타입의 optionDTO에 담아서
            OptionDTO optionDTO = OptionDTO.builder()
                    .idx(option.getIdx())
                    .title(option.getTitle())
                    .build();

            optionDTOList.add(optionDTO);
//          optionDTOList에 추가한다.
        });


        for (int i = 0; i < optionDTOList.size(); i++) {
//            optionDTOList 리스트 크기만큼 아래를 반복한다.
            for (int j = 0; j < optionSelectList.size(); j++) {
//                optionSelectList 리스트 크기만큼 아래를 반복한다.
                OptionSelect optionSelect = optionSelectList.get(j);
//                optionSelectList의 j번째 객체를 optionSelect에 저장.

                if (optionSelect.getOption().getIdx() == optionDTOList.get(i).getIdx()) {
//                    만약 optionSelect의 option의 idx가 optionDTOList의 idx와 같다면,
//                    optionSelectDTO에 optionSelect 엔티티의 idx, title을 저장한다.
                    OptionSelectDTO optionSelectDTO = OptionSelectDTO.builder()
                            .idx(optionSelect.getIdx())
                            .title(optionSelect.getTitle())
                            .price(optionSelect.getPrice())
                            .build();

                    optionDTOList.get(i).getOptionSelectDTOList().add(optionSelectDTO);
//                  optionDTOList의 i번째 OptionSelectDTOList에 optionSelectDTO를 저장한다.
                }
                Collections.sort(optionDTOList.get(i).getOptionSelectDTOList(), new OptionSelectDTOComparator());
            }
        }

        Collections.sort(optionDTOList, new OptionDTOComparator());

        ProductDTO productDTO = ProductDTO.builder()
                .idx(product.getIdx())
                .productName(product.getProductName())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .quantity(product.quantity)
                .productInfo(product.getProductInfo())
                .tags(product.tags)
                .optionDTOList(optionDTOList)
                .build();
//      productDTO에 product 엔티티의 idx, 값을 저장한다.

//      productDTO를 반환.
        return productDTO;
    }

    class OptionDTOComparator implements Comparator<OptionDTO> {
        @Override
        public int compare(OptionDTO optionDTO1, OptionDTO optionDTO2) {
            return ((Integer) optionDTO1.getIdx()).compareTo(optionDTO2.getIdx());
        }
    }

    class OptionSelectDTOComparator implements Comparator<OptionSelectDTO> {
        @Override
        public int compare(OptionSelectDTO optionSelectDTO1, OptionSelectDTO optionSelectDTO2) {
            return ((Integer) optionSelectDTO1.getIdx()).compareTo(optionSelectDTO2.getIdx());
        }
    }

}
