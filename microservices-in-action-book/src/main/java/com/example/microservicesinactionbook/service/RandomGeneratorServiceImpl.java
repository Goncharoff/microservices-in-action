package com.example.microservicesinactionbook.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {
    public static final int MINIMUM_FACTOR = 11;
    public static final int MAXIMUM_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random()
                .nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1)  + MINIMUM_FACTOR;
    }
}
