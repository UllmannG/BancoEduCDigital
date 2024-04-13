package br.com.finance.DigitalBank.service;

import br.com.finance.DigitalBank.api.TaxaRendApi;
import br.com.finance.DigitalBank.dto.ContaDto;
import br.com.finance.DigitalBank.entity.Conta;
import br.com.finance.DigitalBank.entity.ContaCorrente;
import br.com.finance.DigitalBank.entity.ContaPoupanca;
import br.com.finance.DigitalBank.repository.ContaRepository;
import br.com.finance.DigitalBank.util.ContaCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {
    @InjectMocks
    private ContaService contaService;
    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findContaDto() {
        BDDMockito.given(contaRepository.findContaById(1L)).willReturn(ContaCreator.createConta());

        ContaDto contaDto = ContaDto.convertContaToDto(contaRepository.findContaById(1L));

        assertNotNull(contaDto);
        assertEquals(1234, contaDto.getAgencia());

    }

    @Test
    void getSaldo() {
        BDDMockito.given(contaRepository.findContaById(1L)).willReturn(ContaCreator.createConta());

        BigDecimal saldoConta = contaRepository.findContaById(1L).getSaldo();

        assertEquals(new BigDecimal(100), saldoConta);
    }


    @Test
    void realizarTransferenciaPixEntreContas() {
        BDDMockito.given(contaRepository.findContaById(1L)).willReturn(ContaCreator.createConta());

        Conta conta2 = new Conta();
        conta2.setId_conta(2L);
        conta2.setSaldo(new BigDecimal(50)); 

        BDDMockito.given(contaRepository.findContaById(2L)).willReturn(conta2);


        BigDecimal saldo1Bef = contaRepository.findContaById(1L).getSaldo();

        BigDecimal saldo2Bef = conta2.getSaldo();

        contaService.transferenciaPix(1L, 2L, new BigDecimal(20));

        BigDecimal saldo1Aft = contaRepository.findContaById(1L).getSaldo();
        BigDecimal saldo2Aft = conta2.getSaldo();

        Assertions.assertNotEquals(saldo1Bef, saldo1Aft);
        Assertions.assertNotEquals(saldo2Bef, saldo2Aft);

    }

    @Test
    void saveContaCorrente() {

        ContaCorrente contaCorrente = ContaCreator.createContaCorrente();

        contaService.saveContaCorrente(contaCorrente);

        Mockito.verify(contaRepository, Mockito.times(1)).save(Mockito.any(ContaCorrente.class)); // Verifica se o método save() foi chamado.
        BDDMockito.then(contaRepository).should().save(contaCorrente); // Verifica se o método save() foi chamado.
        assertEquals(3, contaCorrente.getId_conta());

    }

    @Test
    void saveContaPoupanca() {

        ContaPoupanca contaPoupanca = ContaCreator.createContaPoupanca();

        contaService.saveContaPoupanca(contaPoupanca);

        Mockito.verify(contaRepository, Mockito.times(1)).save(Mockito.any(ContaPoupanca.class));
        assertEquals(3, contaPoupanca.getId_conta());
        assertEquals(TaxaRendApi.getTaxaCdi(), contaPoupanca.getRendimento());

    }


}