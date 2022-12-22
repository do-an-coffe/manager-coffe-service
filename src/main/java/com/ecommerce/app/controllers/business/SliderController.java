package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.impl.SliderFilterDto;
import com.ecommerce.app.responses.SliderResponse;
import com.ecommerce.domain.entities.business.Slider;
import com.ecommerce.domain.services.impl.business.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slider")
public class SliderController extends BaseController<Slider, Long, SliderResponse, SliderFilterDto> {

  @Autowired
  SliderService sliderService;

  protected SliderController() {
    super(SliderResponse.class, SliderFilterDto.class);
  }

  @PostMapping("/{id}/status")
  SliderResponse changeStatus(@PathVariable Long id){
    return sliderService.changeStatus(id);
  }
}
