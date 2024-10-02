package com.rsg.crypto.repository;

import com.rsg.crypto.model.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoPrice,Long> {
}
