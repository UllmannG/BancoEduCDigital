package br.com.finance.DigitalBank.util;

import br.com.finance.DigitalBank.api.TaxaRendApi;
import br.com.finance.DigitalBank.dto.ContaDto;
import br.com.finance.DigitalBank.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ContaCreator {

    public static Conta createConta() {
        Conta contas = new Conta();
        contas.setId_conta(1L);
        contas.setAgencia(1234);
        contas.setConta(4321);
        contas.setSaldo(new BigDecimal(100));

        return contas;
    }

    public static ContaCorrente createContaCorrente() {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setId_conta(3L);
        contaCorrente.setAgencia(5678);
        contaCorrente.setConta(8765);
        contaCorrente.setSaldo(new BigDecimal(50));
        contaCorrente.setTaxaMensal(new BigDecimal(12));

        return contaCorrente;
    }

    public static ContaPoupanca createContaPoupanca() {
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.setId_conta(3L);
        contaPoupanca.setAgencia(5678);
        contaPoupanca.setConta(8765);
        contaPoupanca.setSaldo(new BigDecimal(50));
        contaPoupanca.setRendimento(new BigDecimal(String.valueOf(TaxaRendApi.getTaxaCdi())));

        return contaPoupanca;
    }



}
