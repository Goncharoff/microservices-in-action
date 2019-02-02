package com.example.microservicesinactionbook.repository;

import com.example.microservicesinactionbook.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {

}
