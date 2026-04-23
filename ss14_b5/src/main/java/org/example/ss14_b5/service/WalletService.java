package org.example.ss14_b5.service;

import org.example.ss14_b5.model.Wallet;
import org.example.ss14_b5.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {

    private WalletRepository walletRepo;

    @Transactional
    public void deposit(Long customerId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền nạp phải > 0");
        }
        Wallet wallet = walletRepo.findById(customerId);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepo.save(wallet);
    }

    @Transactional
    public void decreaseBalance(Long customerId, double amount) {
        Wallet wallet = walletRepo.findById(customerId);
        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Số dư không đủ");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepo.save(wallet);
    }

    public double getBalance(Long customerId) {
        return walletRepo.findById(customerId).getBalance();
    }
}

