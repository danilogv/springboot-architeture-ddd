package com.danilo.springbootarchitetureddd.domain.repository;

import com.danilo.springbootarchitetureddd.domain.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends MongoRepository<Card,String> {

    Card findByNumber(String number);
    List<Card> findAllByOrderByExpirationDateDesc();
    List<Card> findAllByExpirationDateBefore(LocalDate expirationDate);

}