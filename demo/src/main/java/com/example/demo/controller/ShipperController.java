package com.example.demo.controller;

import com.example.demo.model.Shipper;
import com.example.demo.model.Wallet;
import com.example.demo.repository.*;
import com.example.demo.request.ShipperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShipperController {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private WalletRepository walletRepository;

    @PostMapping(value = "/shipper/update")
    public ResponseEntity<String> updateWalletBasedOnValue(@RequestBody ShipperDTO shipperDTO ){
        Shipper shipper = shipperRepository.findById(1).get();
        if(receiptRepository.getStatus(1).equals(0))
            walletRepository.setBalance() += (.1 * productRepository.getTotal());

    }
}
