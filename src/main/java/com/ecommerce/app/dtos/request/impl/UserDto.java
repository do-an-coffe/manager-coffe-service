package com.ecommerce.app.dtos.request.impl;

import com.ecommerce.app.dtos.request.DTO;
import com.ecommerce.domain.entities.author.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.util.Date;

@Data
@JsonTypeName("user_update")
public class UserDto implements DTO<User> {

    @JsonProperty("full_name")
    private String fullname;

    private String phone;

    private String address;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date birthday;
}
