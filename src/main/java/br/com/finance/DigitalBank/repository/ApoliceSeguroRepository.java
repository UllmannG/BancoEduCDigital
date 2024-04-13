package br.com.finance.DigitalBank.repository;

import br.com.finance.DigitalBank.entity.ApoliceSeguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoliceSeguroRepository extends JpaRepository<ApoliceSeguro, Long> {
}
