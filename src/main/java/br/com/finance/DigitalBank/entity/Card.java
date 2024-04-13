package br.com.finance.DigitalBank.entity;

import br.com.finance.DigitalBank.exception.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_card;
    private String password;
    private BigDecimal tax;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "id_conta")
    @JsonBackReference // Ignora a serialização da conta ao serializar um cartão evitando recursão infinita
    private Conta conta;


}
