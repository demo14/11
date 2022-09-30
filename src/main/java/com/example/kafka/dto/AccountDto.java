package com.example.kafka.dto;

import com.example.kafka.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
public class AccountDto{
    private Long id;
    private String name;
    private String lastName;
    private BigDecimal balance;
    private Integer accountNumber;
    private Status status;
}
