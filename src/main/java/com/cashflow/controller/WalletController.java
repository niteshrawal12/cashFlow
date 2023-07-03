package com.cashflow.controller;

import com.cashflow.dto.WalletDTO;
import com.cashflow.entity.Wallet;
import com.cashflow.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody WalletDTO walletDTO) {
        Wallet wallet = walletService.createWallet(walletDTO);
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<List<WalletDTO>> createWallets(@RequestBody List<WalletDTO> walletDTOList) {
        List<WalletDTO> createdWallets = walletService.createWallets(walletDTOList);
        return ResponseEntity.ok(createdWallets);
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        List<Wallet> wallets = walletService.getAllWallets();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        if (wallet != null) {
            return new ResponseEntity<>(wallet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable Long id, @RequestBody WalletDTO walletDTO) {
        Wallet updatedWallet = walletService.updateWallet(id, walletDTO);
        if (updatedWallet != null) {
            return new ResponseEntity<>(updatedWallet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        boolean deleted = walletService.deleteWallet(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
