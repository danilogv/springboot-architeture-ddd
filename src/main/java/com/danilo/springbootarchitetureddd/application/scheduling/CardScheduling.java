package com.danilo.springbootarchitetureddd.application.scheduling;

import com.danilo.springbootarchitetureddd.domain.entity.Card;
import com.danilo.springbootarchitetureddd.infrastructure.design_pattern.facade.FacadeRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

public class CardScheduling extends FacadeRepository {

    @Scheduled(cron = "0 0 * * *")
    public void removeExpiredCards() {
        List<Card> cards = this.card.findAllByExpirationDateBefore(LocalDate.now());

        cards.forEach((card) -> {
            this.card.deleteById(card.getId());
        });

    }

}
