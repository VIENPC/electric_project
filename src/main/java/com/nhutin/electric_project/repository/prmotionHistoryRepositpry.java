package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhutin.electric_project.model.Promotion_Historie;

public interface prmotionHistoryRepositpry extends JpaRepository<Promotion_Historie, Integer> {

    @Query("SELECT o FROM Promotion_Historie o where o.promotion.id= ?1")
    List<Promotion_Historie> findPromoHistory(Integer user);
    
    @Query("SELECT COUNT(ph) FROM Promotion_Historie ph WHERE ph.user.id = ?1")
    Long countUsedPromotionCodes(Integer userId);
    
    @Query("SELECT COUNT(ph) FROM Promotion_Historie ph WHERE ph.promotion.id = ?1")
    Long countPromotionCodes(Integer userId);
}
