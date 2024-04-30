package com.danilo.springbootarchitetureddd.presentation.controller;

import com.danilo.springbootarchitetureddd.infrastructure.design_pattern.facade.FacadeService;
import com.danilo.springbootarchitetureddd.presentation.dto.CardDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/card")
@Log4j2
public class CardController extends FacadeService {

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CardDTO card) {
        log.info("Body sent front-end : " + card);
        this.card.create(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid CardDTO card) {
        log.info("Body sent front-end : " + card);
        this.card.update(card);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String number) {
        log.info("Param number sent front-end : " + number);
        this.card.delete(number);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/number")
    public ResponseEntity<CardDTO> searchOne(@RequestParam String number) {
        log.info("Param number sent front-end : " + number);
        CardDTO card = this.card.searchOne(number);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> searchAll() {
        log.info("Search all cards");
        List<CardDTO> cards = this.card.searchAll();
        return ResponseEntity.status(HttpStatus.OK).body(cards);
    }

}
