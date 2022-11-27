package com.ecommerce.app.dtos.impl;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.domain.entities.business.Slider;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonTypeName("slider")
public class SliderDto implements DTO<Slider> {

    private Long id;

    @NotNull(message = "name not null")
    private String name;

    @NotNull(message = "link not null")
    private String link;

}
