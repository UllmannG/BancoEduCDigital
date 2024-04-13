package br.com.finance.DigitalBank.repository;

import br.com.finance.DigitalBank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c")
    List<Card> findAllCard();

    @Query("SELECT c FROM Card c WHERE c.id_card = :id_card")
    Card findCardById (@Param("id_card") Long id_card);



}
