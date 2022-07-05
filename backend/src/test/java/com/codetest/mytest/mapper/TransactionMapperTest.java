package com.codetest.mytest.mapper;

import com.codetest.mytest.helper.TestHelper;
import com.codetest.mytest.entity.StoreTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TransactionMapperTest {

    @InjectMocks
    private TransactionMapper transactionMapper;

    @Test
    void givenAValidTransactionStringThenReturnAValidTransaction() throws IOException {
        BufferedReader bufferedReader = TestHelper.getBufferedReaderFromFile("transaction.txt", this);
        Optional<StoreTransaction> optionalStoreTransaction = transactionMapper.toTransaction(bufferedReader.readLine());
        assertTrue(optionalStoreTransaction.isPresent());
        StoreTransaction storeTransaction = optionalStoreTransaction.get();
        assertNotNull(storeTransaction.getId());
        assertEquals("DOC_INCOMING", storeTransaction.getTransactionType().toString());
        assertEquals("2019-03-01", storeTransaction.getEventDate().toString());
        assertEquals(new BigDecimal("102.03").setScale(2, RoundingMode.HALF_UP), storeTransaction.getAmount());
        assertEquals("84515254073", storeTransaction.getCpfNumber());
        assertEquals("2344****1222", storeTransaction.getCardNumber());
        assertEquals("12:32:22", storeTransaction.getTransactionHour().toString());
        assertEquals("MARCOS PEREIRA", storeTransaction.getStoreOwnerName());
        assertEquals("MERCADO DA AVENIDA", storeTransaction.getStoreName());
    }
}
