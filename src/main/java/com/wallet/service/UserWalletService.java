package com.wallet.service;

import com.wallet.entity.UserWallet;

public interface UserWalletService {
	
	UserWallet save(UserWallet uw);

	UserWallet findByUserIdAndWalletId(Long user, Long wallet);
}
