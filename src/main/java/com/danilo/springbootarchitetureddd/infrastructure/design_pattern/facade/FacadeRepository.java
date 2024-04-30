package com.danilo.springbootarchitetureddd.infrastructure.design_pattern.facade;

import com.danilo.springbootarchitetureddd.domain.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacadeRepository {

    @Autowired
    protected CardRepository card;

}
