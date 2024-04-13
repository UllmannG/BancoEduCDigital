package br.com.finance.DigitalBank.repository;

import br.com.finance.DigitalBank.entity.Conta;
import br.com.finance.DigitalBank.entity.ContaCorrente;
import br.com.finance.DigitalBank.entity.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT c FROM Conta c WHERE c.id_conta = :id_conta")
    Conta findContaById(@Param("id_conta")Long id_conta);

    @Query("SELECT c FROM ContaCorrente c WHERE c.id_conta = :id_conta")
    ContaCorrente findContaCorrenteById(@Param("id_conta") Long id_conta);

    @Query("SELECT c FROM ContaPoupanca c WHERE c.id_conta = :id_conta")
    ContaPoupanca findContaPoupancaById(@Param("id_conta") Long id_conta);

}


