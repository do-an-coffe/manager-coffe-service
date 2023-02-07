package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Slider;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SliderStorage extends BaseStorage{
  public Page<Slider> findAll(Pageable pageable){
    return sliderRepository.findAll(pageable);
  }

  public Slider save(Slider slider){
    return sliderRepository.save(slider);
  }

  public void delete(Slider slider){
    sliderRepository.delete(slider);
  }

  public Slider findSliderById(Long id){
    return sliderRepository.findSliderById(id);
  }

}
