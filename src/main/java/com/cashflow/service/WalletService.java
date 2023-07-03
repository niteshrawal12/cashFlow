package com.cashflow.service;

import com.cashflow.dto.WalletDTO;
import com.cashflow.entity.Wallet;
import com.cashflow.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(WalletDTO walletDTO) {
        Wallet wallet = new Wallet();
        // Set values from DTO to the entity
        wallet.setName(walletDTO.getName());
        wallet.setBalance(walletDTO.getBalance());

        return walletRepository.save(wallet);
    }

    public List<WalletDTO> createWallets(List<WalletDTO> walletDTOList) {
        List<Wallet> wallets = new ArrayList<>();
        for (WalletDTO walletDTO : walletDTOList) {
            Wallet wallet = new Wallet();
            wallet.setName(walletDTO.getName());
            wallet.setBalance(walletDTO.getBalance());
            wallets.add(wallet);
        }

        List<Wallet> createdWallets = walletRepository.saveAll(wallets);

        return mapWalletsToDTOs(createdWallets);
    }

    private List<WalletDTO> mapWalletsToDTOs(List<Wallet> wallets) {
        List<WalletDTO> walletDTOs = new ArrayList<>();

        for (Wallet wallet : wallets) {
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.setId(wallet.getId());
            walletDTO.setName(wallet.getName());
            walletDTO.setBalance(wallet.getBalance());
            walletDTOs.add(walletDTO);
        }

        return walletDTOs;
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    public Wallet updateWallet(Long id, WalletDTO walletDTO) {
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if (optionalWallet.isPresent()) {
            Wallet wallet = optionalWallet.get();
            // Update wallet details based on the DTO
            wallet.setName(walletDTO.getName());
            wallet.setBalance(walletDTO.getBalance());
            return walletRepository.save(wallet);
        } else {
            return null; // Wallet with the given ID not found
        }
    }

    public boolean deleteWallet(Long id) {
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if (optionalWallet.isPresent()) {
            walletRepository.deleteById(id);
            return true;
        } else {
            return false; // Wallet with the given ID not found
        }
    }

}
