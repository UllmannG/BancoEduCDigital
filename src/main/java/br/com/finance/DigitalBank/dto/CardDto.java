package br.com.finance.DigitalBank.dto;

import br.com.finance.DigitalBank.entity.Card;
import br.com.finance.DigitalBank.entity.Conta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDto {

    private Long id_card;
    private String password;
    private BigDecimal tax;
    private Boolean active;
    private Conta conta;



    public static CardDto convertCardToDto (Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId_card(card.getId_card());
        cardDto.setPassword(card.getPassword());
        cardDto.setTax(card.getTax());
        cardDto.setActive(card.getActive());
        cardDto.setConta(card.getConta());

        return cardDto;
    }

}
