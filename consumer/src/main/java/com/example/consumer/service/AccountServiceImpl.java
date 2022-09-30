package com.example.consumer.service;

import com.example.consumer.dto.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final ObjectMapper mapper;



    @Override
    @KafkaListener(id = "test",topics = {"test"},containerFactory = "batchFactory")
    public void consume(List<AccountDto> dto) {
        log.info("=> cosumed {}",dto);

//        repository.saveAll(dto);
    }


    private String writeValueAsString(AccountDto dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
