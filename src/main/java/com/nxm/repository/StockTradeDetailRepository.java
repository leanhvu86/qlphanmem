package com.nxm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nxm.model.StockTrade;
import com.nxm.model.StockTradeDetail;
@Repository(value = "stockTradeDetailRepository")
public interface StockTradeDetailRepository extends CrudRepository<StockTradeDetail, Long> {
	
	List<StockTradeDetail> findByStockTrade(StockTrade stockTrade);
}
