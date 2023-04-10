package com.backdrop.data.dtos.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Account {
    private Data data;
    @JsonIgnore
    private boolean status;
    @JsonIgnore private String message;
}
