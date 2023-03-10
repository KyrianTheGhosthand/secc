package com.example.demo.repository;
import com.example.demo.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> getStatus(int status);
}
