package com.wallet.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

@Repository
public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {

	Page<WalletItem>findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Long wallet, Date init, Date end, Pageable pag);
	
	List<WalletItem> findByWalletIdAndType(Long wallet, TypeEnum type);

	@Query(value = "select sum(value) from WalletItem wi where wi.wallet.id = :walletId")
	BigDecimal sumByWalletId(@Param("walletId") Long walletId);
}
