package com.example.consumer.dto;

import com.example.consumer.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountDto extends AbstractDto{
    private Long id;
    private String name;
    private String lastName;
    private BigDecimal balance;
    private Integer accountNumber;
    private Status status;
}
