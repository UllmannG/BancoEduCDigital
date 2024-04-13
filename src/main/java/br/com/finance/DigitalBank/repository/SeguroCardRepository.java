package br.com.finance.DigitalBank.repository;

import br.com.finance.DigitalBank.entity.ApoliceSeguro;
import br.com.finance.DigitalBank.entity.SeguroCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguroCardRepository extends JpaRepository<SeguroCard, Long> {
}
