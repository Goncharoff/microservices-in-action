package com.example.microservicesinactionbook.service;

public interface RandomGeneratorService {
    /**
     * Creates a Multiplication object with two randomlygenerated factors
     * between 11 and 99.
     * *
     *
     * @return a Multiplication object with random factors
     */
    int generateRandomFactor();
}
