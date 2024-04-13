package br.com.finance.DigitalBank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "Conta_Corrente")
public class ContaCorrente extends Conta{
    @Column(name = "taxa_mensal")
    private BigDecimal taxaMensal;
    @Column(name = "date_cobranca")
    private LocalDate dateCobranca;


    public void cobrarTaxaMensal() {
        LocalDate today = LocalDate.now();
        taxaMensal = BigDecimal.valueOf(12.0);

        if (dateCobranca == null || today.getMonthValue() != dateCobranca.getMonthValue()) {
            setSaldo(getSaldo().subtract(taxaMensal));
            setDateCobranca(today);
        }
    }

}
