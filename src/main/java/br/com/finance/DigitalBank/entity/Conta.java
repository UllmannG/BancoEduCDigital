package br.com.finance.DigitalBank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_conta;
    @ManyToOne
    @JoinColumn(name = "id_client")
    @JsonBackReference
    private Client client;
    private Integer agencia;
    private Integer conta;
    private BigDecimal saldo;
    @OneToMany(mappedBy ="conta", cascade = CascadeType.ALL)
    @JsonManagedReference // Ignora a serialização da conta ao serializar um cartão evitando recursão infinita
    private List<Card> cards;


}
