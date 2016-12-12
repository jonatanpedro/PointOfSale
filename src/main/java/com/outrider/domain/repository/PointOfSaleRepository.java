package com.outrider.domain.repository;

import com.outrider.domain.entity.PointOfSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jonat on 09/12/2016.
 */
public interface PointOfSaleRepository extends JpaRepository<PointOfSale,Long>{
    List<PointOfSale> findByTradingNameStartsWithIgnoreCase(String tradingName);
}
