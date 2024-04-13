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
@Table(name = "Credit_Card")
public class CreditCard extends Card{
    @Column(name = "credit_limit")
    private BigDecimal creditLimit;
    private BigDecimal fatura;
    private LocalDate vencMes;
    @OneToOne(mappedBy = "creditCard")
    private ApoliceSeguro apoliceSeguro;
    @OneToOne(mappedBy = "credit_card")
    private SeguroCard seguroCard;


    public void resetLimitMonth() {
        LocalDate today = LocalDate.now();
        if (getVencMes() == null || getVencMes().getMonth() != today.getMonth()) {
            setCreditLimit(BigDecimal.ZERO);
            setVencMes(today);
        }
    }

}
