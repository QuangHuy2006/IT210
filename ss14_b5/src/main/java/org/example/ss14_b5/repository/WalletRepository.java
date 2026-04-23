package org.example.ss14_b5.repository;

import org.example.ss14_b5.model.Wallet;


public interface WalletRepository {
    Wallet findById(Long id);
    void save(Wallet wallet);
}
