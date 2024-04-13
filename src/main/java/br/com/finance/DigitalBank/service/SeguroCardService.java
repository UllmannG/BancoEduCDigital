package br.com.finance.DigitalBank.service;

import br.com.finance.DigitalBank.entity.ApoliceSeguro;
import br.com.finance.DigitalBank.entity.Conta;
import br.com.finance.DigitalBank.entity.CreditCard;
import br.com.finance.DigitalBank.entity.SeguroCard;
import br.com.finance.DigitalBank.exception.ExceptionMessage;
import br.com.finance.DigitalBank.repository.ApoliceSeguroRepository;
import br.com.finance.DigitalBank.repository.ContaRepository;
import br.com.finance.DigitalBank.repository.CreditCardRepository;
import br.com.finance.DigitalBank.repository.SeguroCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SeguroCardService {

    @Autowired
    private SeguroCardRepository seguroCardRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private ApoliceSeguroRepository apoliceSeguroRepository;
    @Autowired
    private ContaRepository contaRepository;


    public SeguroCard contratarSeguro(Long idCard, SeguroCard seguroCard) throws ExceptionMessage {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(idCard);
        CreditCard creditCard = optionalCreditCard.orElseThrow(() -> new ExceptionMessage("Cartão não encontrado!"));
        Conta conta = creditCard.getConta();

        if (seguroCard.getDataContrat().equals(0) || seguroCard.getDataContrat().getMonth() != LocalDate.now().getMonth()
        && seguroCard.getDataContrat().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
            conta.setSaldo(conta.getSaldo().subtract(seguroCard.getValor()));
        }

        seguroCard.setCredit_card(creditCard);

        ApoliceSeguro apoliceSeguro = new ApoliceSeguro();
        apoliceSeguro.setDataContratacao(seguroCard.getDataContrat());
        apoliceSeguro.setCreditCard(creditCard);
        apoliceSeguro.setNumeroApolice((long) Math.pow(10, 10));

        seguroCard.setApoliceSeguro(apoliceSeguro);

        apoliceSeguroRepository.save(apoliceSeguro);
        seguroCardRepository.save(seguroCard);
        contaRepository.save(conta);

        return seguroCard;
    }


}
