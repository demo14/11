package com.example.kafka.service;

import com.example.kafka.dto.AccountDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetAllAccountIntegration {
    List<AccountDto> getAllAccount() throws ExecutionException, InterruptedException;
}
