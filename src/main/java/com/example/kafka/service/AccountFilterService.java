package com.example.kafka.service;

import java.util.concurrent.ExecutionException;

public interface AccountFilterService {
    Object produce() throws ExecutionException, InterruptedException;
}
