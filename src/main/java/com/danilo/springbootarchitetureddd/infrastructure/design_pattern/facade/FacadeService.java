package com.danilo.springbootarchitetureddd.infrastructure.design_pattern.facade;

import com.danilo.springbootarchitetureddd.application.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacadeService {

    @Autowired
    protected CardService card;

}
