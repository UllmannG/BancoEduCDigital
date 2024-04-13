package br.com.finance.DigitalBank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ApoliceSeguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApolice;
    private LocalDate dataContratacao;
    private Long numeroApolice;
    @OneToOne
    @JoinColumn(name ="id_credit_card", referencedColumnName = "id_card")
    private CreditCard creditCard;
    @OneToOne(mappedBy = "apoliceSeguro")
    private SeguroCard seguroCard;
}
