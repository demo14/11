package com.example.kafka.service.serviceImpl;

import com.example.kafka.dto.AccountDto;
import com.example.kafka.service.AccountFilterService;
import com.example.kafka.service.GetAllAccountIntegration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountFilterServiceImpl implements AccountFilterService {

    private final GetAllAccountIntegration integration;
    private final KafkaTemplate<Long, List<AccountDto>> template;
    private final ObjectMapper mapper;


    @Override
    public Object produce() throws ExecutionException, InterruptedException {
        List<AccountDto> accountDtoList = integration.getAllAccount();
        List<AccountDto> collect = accountDtoList.stream()
                .filter(accountDto -> accountDto.getName().endsWith("р"))
                .collect(Collectors.toList());

            log.info("<= send {}",collect);

        return template.send("test", collect);
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
