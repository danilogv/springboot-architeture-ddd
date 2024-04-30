package com.danilo.springbootarchitetureddd.infrastructure.design_pattern.singleton;


import com.danilo.springbootarchitetureddd.domain.entity.Card;
import com.danilo.springbootarchitetureddd.presentation.dto.CardDTO;
import com.danilo.springbootarchitetureddd.presentation.dto.ErrorDTO;
import lombok.Getter;

public class Singleton {

    @Getter
    private static Card card = new Card();

    @Getter
    private static CardDTO cardDTO = new CardDTO();

    @Getter
    private static ErrorDTO errorDTO = new ErrorDTO();

    public static void resetCard() {
        card = new Card();
    }

    public static void resetCardDTO() {
        cardDTO = new CardDTO();
    }

    public static void resetErrorDTO() {
        errorDTO = new ErrorDTO();
    }

}
