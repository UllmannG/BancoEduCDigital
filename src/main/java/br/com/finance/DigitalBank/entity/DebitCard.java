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
public class DebitCard extends Card{
    private BigDecimal dailyLimit;
    private LocalDate dayLimit;


    public void resetDailyLimit() {
        LocalDate today = LocalDate.now();

        if (getDayLimit() == null || getDayLimit().getDayOfMonth() != today.getDayOfMonth()) {
            setDailyLimit(BigDecimal.ZERO);
            setDayLimit(today);
        }
    }
}
