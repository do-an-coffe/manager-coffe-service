package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.impl.SliderDto;
import com.ecommerce.app.responses.SliderResponse;
import com.ecommerce.app.responses.base.PageResponse;
import com.ecommerce.domain.entities.business.Slider;
import com.ecommerce.domain.services.impl.business.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/slider")
public class SliderController {

  @Autowired private SliderService sliderService;
  @PostMapping("/{id}/status")
  public SliderResponse changeStatus(@PathVariable Long id){
    return sliderService.changeStatus(id);
  }

  @GetMapping("all")
  public PageResponse<SliderResponse> getAll(Pageable pageable){
    return PageResponse.createFrom(sliderService.getAll(pageable));
  }

  @GetMapping("filter")
  public PageResponse<SliderResponse> filter(Pageable pageable){
    return PageResponse.createFrom(sliderService.getAll(pageable));
  }

  @GetMapping("{id}")
  public SliderResponse detail(@PathVariable Long id){
    return sliderService.detail(id);
  }

  @PostMapping()
  public Slider create(@RequestBody @Valid SliderDto sliderDto){
    return sliderService.create(sliderDto);
  }

  @PatchMapping("{id}")
  public Slider update(@PathVariable Long id, @RequestBody @Valid SliderDto sliderDto){
    return sliderService.update(id, sliderDto);
  }

  @DeleteMapping("{id}")
  public boolean delete(@PathVariable Long id){
    return sliderService.delete(id);
  }
}
