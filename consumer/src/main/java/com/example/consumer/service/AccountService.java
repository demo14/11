package com.example.consumer.service;

import com.example.consumer.dto.AccountDto;

import java.util.List;

public interface AccountService {

    void consume(List<AccountDto> dto);
}
