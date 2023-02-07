package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.impl.SliderDto;
import com.ecommerce.app.responses.SliderResponse;
import com.ecommerce.domain.entities.business.Slider;
import com.ecommerce.domain.services.base.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SliderService extends BaseService {

  public Page<SliderResponse> getAll(Pageable pageable){
    Page<Slider> sliderPage = sliderStorage.findAll(pageable);
    return mapperUtil.mapEntityPageIntoDtoPage(sliderPage, SliderResponse.class);
  }

  public SliderResponse detail(Long id){
    return modelMapper.toSliderResponse(findSliderById(id));
  }

  public Slider create(SliderDto dto) {
    Slider slider = Slider.builder()
            .name(dto.getName())
            .link(dto.getLink())
            .status(true)
            .build();

    return sliderStorage.save(slider);
  }

  public Slider update(Long id, SliderDto dto) {
    Slider slider = findSliderById(id);
    slider.setName(dto.getName());
    slider.setLink(dto.getLink());

    return sliderStorage.save(slider);
  }

  public boolean delete(Long id) {
    Slider slider = findSliderById(id);

    sliderStorage.delete(slider);
    return true;
  }

  public SliderResponse changeStatus(Long id) {
    Slider slider = findSliderById(id);
    slider.setStatus(!slider.getStatus());
    return modelMapper.toSliderResponse(sliderStorage.save(slider));
  }
}
