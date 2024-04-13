package br.com.finance.DigitalBank.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client;
    private Long cpf;
    private String name;
    @Column(name = "data_nasc")
    private LocalDate dataNasc;
    @JoinColumn(name = "id_endereco")
    @ManyToOne
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    @Column(name = "client_category")
    private ClientCategory clientCategory;
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<Conta> conta;

}
