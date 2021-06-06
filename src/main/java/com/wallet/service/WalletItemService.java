package com.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

public interface WalletItemService {

	WalletItem save(WalletItem walletItem);
	
	Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);
	
	List<WalletItem> findByWalletAndType(Long idWalletItem, TypeEnum type);
	
	BigDecimal sumByWalletId(Long idWallet);

	Optional<WalletItem> findById(Long id);
	
	void deleteById(Long id);
}
