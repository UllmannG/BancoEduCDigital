package br.com.finance.DigitalBank.dto;

import br.com.finance.DigitalBank.entity.Card;
import br.com.finance.DigitalBank.entity.Client;
import br.com.finance.DigitalBank.entity.Conta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaDto {

    private Long id_conta;
    private Client client;
    private Integer agencia;
    private Integer conta;
    private BigDecimal saldo;
    private List<Card> cards;



    public static ContaDto convertContaToDto (Conta conta) {

        ContaDto contaDto = new ContaDto();
        contaDto.setId_conta(conta.getId_conta());
        contaDto.setClient(conta.getClient());
        contaDto.setAgencia(conta.getAgencia());
        contaDto.setConta(conta.getConta());
        contaDto.setSaldo(conta.getSaldo());
        contaDto.setCards(conta.getCards());

        return contaDto;
    }


}
