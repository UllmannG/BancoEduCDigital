package br.com.finance.DigitalBank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SeguroCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String descricao;
    private Boolean active;
    @OneToOne
    private ApoliceSeguro apoliceSeguro;
    @OneToOne
    private CreditCard credit_card;
    private LocalDate dataContrat;
}
