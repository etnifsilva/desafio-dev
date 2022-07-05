package com.codetest.mytest.repository;

import com.codetest.mytest.entity.StoreTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<StoreTransaction, Integer> {

    List<StoreTransaction> findByStoreNameOrderByEventDateAscTransactionHourAsc(String storeName);

    @Query(value = "select distinct store_name from mytest_schema.store_transaction", nativeQuery = true)
    List<String> getStores();
}
