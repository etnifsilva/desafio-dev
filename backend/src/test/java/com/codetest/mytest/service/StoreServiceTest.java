package com.codetest.mytest.service;


import com.codetest.mytest.helper.TestHelper;
import com.codetest.mytest.dto.StoreReportDto;
import com.codetest.mytest.entity.StoreTransaction;
import com.codetest.mytest.enums.TransactionType;
import com.codetest.mytest.mapper.TransactionMapper;
import com.codetest.mytest.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private StoreService storeService;

    private List<StoreTransaction> storeTransactionList;

    @BeforeEach
    void setUp() {
        this.storeTransactionList = List.of(
                StoreTransaction.builder()
                        .id("58f80327-3c81-4fb2-974b-12601f89e316")
                        .transactionType(TransactionType.DEBIT)
                        .eventDate(LocalDate.parse("2019-03-01"))
                        .amount(new BigDecimal("105.50").setScale(2, RoundingMode.HALF_UP))
                        .cpfNumber("84515254073")
                        .cardNumber("2344****1222")
                        .transactionHour(LocalTime.parse("12:32:22"))
                        .storeOwnerName("MARCOS PEREIRA")
                        .storeName("MERCADO DA AVENIDA")
                        .build(),
                StoreTransaction.builder()
                        .id("58f80327-3c81-4fb2-974b-12601f89e316")
                        .transactionType(TransactionType.CREDIT)
                        .eventDate(LocalDate.parse("2019-03-02"))
                        .amount(new BigDecimal("200").setScale(2, RoundingMode.HALF_UP))
                        .cpfNumber("84515254073")
                        .cardNumber("2344****1222")
                        .transactionHour(LocalTime.parse("12:32:22"))
                        .storeOwnerName("MARCOS PEREIRA")
                        .storeName("MERCADO DA AVENIDA")
                        .build(),
                StoreTransaction.builder()
                        .id("58f80327-3c81-4fb2-974b-12601f89e316")
                        .transactionType(TransactionType.LOAN)
                        .eventDate(LocalDate.parse("2019-03-01"))
                        .amount(new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP))
                        .cpfNumber("84515254073")
                        .cardNumber("2344****1222")
                        .transactionHour(LocalTime.parse("13:32:22"))
                        .storeOwnerName("MARCOS PEREIRA")
                        .storeName("MERCADO DA AVENIDA")
                        .build()
        );
    }

    @Test
    void givenAValidTransactionFileThenSaveItInDatabase() throws IOException {
        when(transactionMapper.toTransaction(anyString()))
                .thenReturn(Optional.of(StoreTransaction.builder().build()))
                .thenReturn(Optional.of(StoreTransaction.builder().build()));

        when(transactionRepository.save(any(StoreTransaction.class)))
                .thenReturn(StoreTransaction.builder().build())
                .thenReturn(StoreTransaction.builder().build());

        InputStream inputStream = TestHelper.getInputStreamFromFile("transaction.txt", this);
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "CNAB.txt",
                MediaType.TEXT_PLAIN_VALUE,
                inputStream.readAllBytes()
        );

        List<StoreTransaction> storeTransactions = storeService.saveTransactionsByFile(file);
        assertNotNull(storeTransactions);
        assertEquals(2, storeTransactions.size());
    }

    @Test
    void givenReceivedARequestThenReturnTheStoreReport() {
        when(transactionRepository.getStores()).thenReturn(List.of("MERCADO DA AVENIDA"));
        when(transactionRepository.findByStoreNameOrderByEventDateAscTransactionHourAsc(anyString()))
                .thenReturn(storeTransactionList);

        StoreReportDto storeReport = storeService.getStoreReport();

        assertEquals("MERCADO DA AVENIDA", storeReport.getStores().get(0).getName());
        assertEquals(
                new BigDecimal("205.50").setScale(2, RoundingMode.HALF_UP),
                storeReport.getStores().get(0).getTotal()
        );
    }
}
