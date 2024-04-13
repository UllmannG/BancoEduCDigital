package br.com.finance.DigitalBank.dto;

import br.com.finance.DigitalBank.entity.Card;
import br.com.finance.DigitalBank.entity.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardDto extends CardDto{
    private BigDecimal creditLimit;
    private BigDecimal fatura;


    public static CreditCardDto convertCardToDto (CreditCard creditCard) {
        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setId_card(creditCard.getId_card());
        creditCardDto.setPassword(creditCard.getPassword());
        creditCardDto.setTax(creditCard.getTax());
        creditCardDto.setActive(creditCard.getActive());
        creditCardDto.setConta(creditCard.getConta());
        creditCardDto.setCreditLimit(creditCard.getCreditLimit());
        creditCardDto.setFatura(creditCard.getFatura());

        return creditCardDto;
    }

}
