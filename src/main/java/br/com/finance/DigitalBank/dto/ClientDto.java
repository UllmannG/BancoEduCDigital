package br.com.finance.DigitalBank.dto;

import br.com.finance.DigitalBank.entity.Client;
import br.com.finance.DigitalBank.entity.ClientCategory;
import br.com.finance.DigitalBank.entity.Conta;
import br.com.finance.DigitalBank.entity.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDto {

    private Long id_client;
    private Long cpf;
    private String name;
    private LocalDate dataNasc;
    private Endereco endereco;
    private ClientCategory clientCategory;
    private List<Conta> conta;



    public static ClientDto convertClientToDto (Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setId_client(clientDto.getId_client());
        clientDto.setCpf(client.getCpf());
        clientDto.setName(client.getName());
        clientDto.setDataNasc(client.getDataNasc());
        clientDto.setEndereco(client.getEndereco());
        clientDto.setClientCategory(client.getClientCategory());
        clientDto.setConta(client.getConta());

        return clientDto;
    }


}
