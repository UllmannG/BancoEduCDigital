package br.com.finance.DigitalBank.controller;

import br.com.finance.DigitalBank.api.TaxaRendApi;
import br.com.finance.DigitalBank.dto.ContaDto;
import br.com.finance.DigitalBank.entity.Conta;
import br.com.finance.DigitalBank.entity.ContaCorrente;
import br.com.finance.DigitalBank.entity.ContaPoupanca;
import br.com.finance.DigitalBank.service.ContaService;
import br.com.finance.DigitalBank.util.ContaCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Indica ao Spring para não usar o próprio ContaService, mas sim simulá-lo
    private ContaService contaService;

    @Autowired
    private JacksonTester<ContaCorrente> jsonContaCorrente; // Classe que gera o JSON

    @Autowired
    private JacksonTester<ContaPoupanca> jsonContaPoupanca;


    @Test
    void shouldReturnContaWithFindContaById() throws Exception {

        Long idConta = 1L;
        Conta conta = ContaCreator.createConta();

        BDDMockito.given(contaService.findContaById(idConta)).willReturn(ContaDto.convertContaToDto(conta));

        mockMvc.perform(get("/conta/{id}", idConta))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnSaldoWithGetSaldo() throws Exception {

        Long idConta = 1L;

        BDDMockito.given(contaService.getSaldo(idConta)).willReturn(new BigDecimal(100));

        mockMvc.perform(get("/conta/{idConta}/saldo", idConta))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("100"));

    }

    @Test
    void shouldReturnStringWithTransferenciaPix() throws Exception {

        Long idOrigem = 1L;
        Long idDestino = 2L;
        BigDecimal valor = new BigDecimal(100);

        Mockito.doNothing().when(contaService).transferenciaPix(idOrigem, idDestino, valor);

        mockMvc.perform(put("/conta/transfPix/{idDestino}", idDestino) // Teste para verificar se o método retorna a mensagem e o código response esperado.
                .param("idOrigem", idOrigem.toString())
                .param("valor", valor.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Transferência realizada com sucesso!"));


    }

    @Test
    void shouldReturn201WithSaveContaCorrente() throws Exception {

        ContaCorrente contaCorrente = new ContaCorrente(new BigDecimal(12), LocalDate.of(2024, 7, 15));

        MockHttpServletResponse response = mockMvc.perform(
                post("/conta/saveContaCorrente")
                        .content(jsonContaCorrente.write(contaCorrente).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

    }

    @Test
    void shouldReturn201WithSaveContaPoupanca() throws Exception {

        ContaPoupanca contaPoupanca = new ContaPoupanca(TaxaRendApi.getTaxaCdi(), LocalDate.of(2024, 6, 10));

        MockHttpServletResponse response = mockMvc.perform(
                post("/conta/saveContaPoupanca")
                        .content(jsonContaPoupanca.write(contaPoupanca).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

    }

}