package com.backdrop.data.dtos.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data{
    private String account_name;
    private String account_number;
    @JsonIgnore
    private String bank_id;
}