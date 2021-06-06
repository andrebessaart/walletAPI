package com.wallet.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.service.WalletItemService;
import com.wallet.util.enums.TypeEnum;

@Service
public class WalletItemServiceImpl implements WalletItemService {

	@Autowired
	WalletItemRepository repository;
	
	@Value("${pagination.itens_per_page}")
	private int itensPerPage;
	
	@Override
	@CacheEvict(value = "findByWalletAndType", allEntries = true)
	public WalletItem save(WalletItem i) {
		return repository.save(i);
	}
	
	@Override
	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page) {
		
		PageRequest pg = PageRequest.of(page, itensPerPage);
		
		return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, start, end, pg);
	}

	@Override
	@Cacheable(value= "findByWalletAndType")
	public List<WalletItem> findByWalletAndType(Long idWalletItem, TypeEnum type) {
		
		return repository.findByWalletIdAndType(idWalletItem, type);
	}

	@Override
	public BigDecimal sumByWalletId(Long idWallet) {
		
		return repository.sumByWalletId(idWallet);
	}

	@Override
	public Optional<WalletItem> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@CacheEvict(value = "findByWalletAndType", allEntries = true)
	public void deleteById(Long id) {
         repository.deleteById(id);
	}
	
	
}
