package com.idus.web.product.optionSelect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionSelectService {
    @Autowired
    OptionSelectRepository optionSelectRepository;

    public void deleteService(int idx){
        OptionSelect optionSelect = new OptionSelect();
        optionSelect.setIdx(idx);

        optionSelectRepository.delete(optionSelect);
    }
}
