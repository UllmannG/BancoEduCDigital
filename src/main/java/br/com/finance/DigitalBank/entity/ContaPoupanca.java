package br.com.finance.DigitalBank.entity;

import br.com.finance.DigitalBank.api.TaxaRendApi;
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
@Table(name = "Conta_Poupanca")
public class ContaPoupanca extends Conta{
    private BigDecimal rendimento;
    private LocalDate dataRendimento;

    public void taxaRendimento() {
        LocalDate today = LocalDate.now();
        TaxaRendApi taxaRendApi = new TaxaRendApi();

        rendimento = taxaRendApi.getTaxaCdi();

        if (dataRendimento == null || today.getMonthValue() != dataRendimento.getMonthValue()) {
            setSaldo(getSaldo().add(rendimento));
            setDataRendimento(today);
        }
    }

}
