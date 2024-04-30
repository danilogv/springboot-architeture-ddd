package com.danilo.springbootarchitetureddd.application.service;

import com.danilo.springbootarchitetureddd.domain.entity.Card;
import com.danilo.springbootarchitetureddd.infrastructure.design_pattern.facade.FacadeRepository;
import com.danilo.springbootarchitetureddd.infrastructure.design_pattern.singleton.Singleton;
import com.danilo.springbootarchitetureddd.infrastructure.utility.FunctionUtility;
import com.danilo.springbootarchitetureddd.presentation.dto.CardDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class CardService extends FacadeRepository {

    @Transactional
    public void create(CardDTO dto) {
        Card card = this.card.findByNumber(dto.getNumber());

        if (Objects.nonNull(card)) {
            String msg = "Card exists in database.";
            log.error(msg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }

        log.info("Card that will be created: " + dto);
        this.createOrUpdate(dto);
    }

    @Transactional
    public void update(CardDTO dto) {
        this.getCard(dto.getNumber());
        log.info("Card that will be updated: " + dto);
        this.createOrUpdate(dto);
    }

    @Transactional
    public void delete(String number) {
        FunctionUtility.validateNumber(number);
        Card card = this.getCard(number);
        log.info("Card that will be removed: " + card);
        this.card.deleteById(card.getId());
    }

    @Transactional(readOnly = true)
    public CardDTO searchOne(String number) {
        Card card = this.getCard(number);
        CardDTO dto = Singleton.getCardDTO();
        BeanUtils.copyProperties(card,dto);
        log.info("Card returned to front-end: " + dto);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<CardDTO> searchAll() {
        List<Card> cards = this.card.findAllByOrderByExpirationDateDesc();

        if (cards.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found.");
        }

        List<CardDTO> cardsResponse = new ArrayList<>();

        cards.forEach((card) -> {
            Singleton.resetCardDTO();
            CardDTO dto = Singleton.getCardDTO();
            BeanUtils.copyProperties(card,dto);
            cardsResponse.add(dto);
        });

        log.info("Cards returned to front-end: " + cardsResponse);

        return cardsResponse;
    }

    private void createOrUpdate(CardDTO dto) {
        FunctionUtility.validateNumber(dto.getNumber());
        FunctionUtility.validateExpirationDate(dto.getExpirationDate());
        Card card = Singleton.getCard();
        BeanUtils.copyProperties(dto,card);
        this.card.save(card);
    }

    private Card getCard(String number) {
        Card card = this.card.findByNumber(number);

        if (Objects.isNull(card)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found.");
        }

        return card;
    }

}
