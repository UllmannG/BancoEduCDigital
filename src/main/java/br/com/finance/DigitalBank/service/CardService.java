package br.com.finance.DigitalBank.service;

import br.com.finance.DigitalBank.dto.CardDto;
import br.com.finance.DigitalBank.dto.CreditCardDto;
import br.com.finance.DigitalBank.entity.Card;
import br.com.finance.DigitalBank.entity.Conta;
import br.com.finance.DigitalBank.entity.CreditCard;
import br.com.finance.DigitalBank.entity.DebitCard;
import br.com.finance.DigitalBank.exception.ExceptionMessage;
import br.com.finance.DigitalBank.repository.CardRepository;
import br.com.finance.DigitalBank.repository.ContaRepository;
import br.com.finance.DigitalBank.repository.CreditCardRepository;
import br.com.finance.DigitalBank.validation.PasswordDigitsValidation;
import br.com.finance.DigitalBank.validation.RepeatPasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private RepeatPasswordValidation repeatPasswordValidation;
    @Autowired
    private PasswordDigitsValidation passwordDigitsValidation;

    public List<CardDto> findAllCard() {

        List<Card> card = cardRepository.findAllCard();

        return card.stream().map(CardDto::convertCardToDto)
                .collect(Collectors.toList());

    }

    public CardDto findCardById(Long id) {
        Card card = cardRepository.findCardById(id);

        CardDto cardDto = CardDto.convertCardToDto(card);

        return cardDto;

    }

    public List<CreditCardDto> findAllCreditCard(){
        List<CreditCard> creditCard = creditCardRepository.findAll();

        return creditCard.stream().map(CreditCardDto::convertCardToDto).collect(Collectors.toList());
    }

    public CardDto saveCard (Card card) throws ExceptionMessage {
        if (passwordDigitsValidation.passwordDigitsValidation(card.getPassword())) {
            throw new ExceptionMessage("A senha deve conter 8 dígitos ou mais");
        } else {
            cardRepository.save(card);
        }
        return CardDto.convertCardToDto(card);
    }

    public CreditCard generateCreditCard (CreditCard creditCard) throws ExceptionMessage{
        if (creditCard.getConta().equals(true)) {
            if (passwordDigitsValidation.passwordDigitsValidation(creditCard.getPassword())) {
                throw new ExceptionMessage("A senha deve conter 8 dígitos ou mais");
            } else {
                return cardRepository.save(creditCard);
            }
        } else {
            new ExceptionMessage("Nenhuma conta encontrada");
        }
        return creditCard;
    }

    public DebitCard generateDebitCard (DebitCard debitCard) throws ExceptionMessage{
        if (debitCard.getConta().equals(true)) {
            if (passwordDigitsValidation.passwordDigitsValidation(debitCard.getPassword())) {
                throw new ExceptionMessage("A senha deve conter 8 dígitos ou mais");
            } else {
                return cardRepository.save(debitCard);
            }
        } else {
            new ExceptionMessage("Nenhuma conta encontrada");
        }
        return debitCard;
    }

    public CardDto alterarSenha (Long id, Card card) throws ExceptionMessage {
        Card card1 = cardRepository.findCardById(id);
        if (repeatPasswordValidation.repeatPasswordValidation(id, card.getPassword())){
            throw new ExceptionMessage("A senha é a mesma que a anterior");
        } else if (passwordDigitsValidation.passwordDigitsValidation(card.getPassword())) {
            throw new ExceptionMessage("A senha deve conter 8 dígitos ou mais");
        } else {
            card1.setPassword(card.getPassword());
            cardRepository.save(card1);
        }
        return CardDto.convertCardToDto(card1);
    }

    public CardDto desativarCard (Long id) throws ExceptionMessage {
        Card card1 = cardRepository.findCardById(id);
        if (card1.getActive().equals(true)){
            card1.setActive(false);
            cardRepository.save(card1);
        }else {
            throw new ExceptionMessage("Cartão já desativado");
        }
        return CardDto.convertCardToDto(card1);
    }

    public CardDto ativarCard (Long id) throws ExceptionMessage {
        Card card1 = cardRepository.findCardById(id);
        if (card1.getActive().equals(false)){
            card1.setActive(true);
            cardRepository.save(card1);
        }else {
            throw new ExceptionMessage("Cartão já ativado");
        }
        return CardDto.convertCardToDto(card1);
    }

    public DebitCard changeDayLimits (Long id, BigDecimal limit) {
        DebitCard debitCard = (DebitCard) cardRepository.findCardById(id);
        debitCard.setDailyLimit(limit);
        return cardRepository.save(debitCard);
    }

    public CreditCard payCreditCard (Long id, BigDecimal value) throws ExceptionMessage {
        CreditCard card = (CreditCard) cardRepository.findCardById(id);
        card.resetLimitMonth();

        if (card.getCreditLimit().compareTo(value) < 0){
            throw new ExceptionMessage("Limite excedido!");
        }else {
            card.setFatura(card.getFatura().add(value));
            card.setCreditLimit(card.getCreditLimit().subtract(value));
            cardRepository.save(card);
        }
        return card;
    }

    public DebitCard payDebitCard (Long id, BigDecimal value) throws ExceptionMessage {
        DebitCard card = (DebitCard) cardRepository.findCardById(id);
        card.resetDailyLimit();
        Conta conta = card.getConta();

        if (conta.getSaldo().compareTo(value) < 0){
            throw new ExceptionMessage("Sem saldo suficiente!");
        }else {
            card.setDailyLimit(card.getDailyLimit().subtract(value));
            conta.setSaldo(conta.getSaldo().subtract(value));
            cardRepository.save(card);
            contaRepository.save(conta);
        }
        return card;
    }

}
