package com.nxm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nxm.model.StockTrade;
@Repository(value = "stockTradeRepository")
public interface StockTradeRepository extends CrudRepository<StockTrade, Long> {
	

	Page<StockTrade> findAll(Pageable pageable);
}
