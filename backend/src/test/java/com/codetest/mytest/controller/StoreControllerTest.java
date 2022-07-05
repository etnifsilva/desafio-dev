package com.codetest.mytest.controller;

import com.codetest.mytest.helper.TestHelper;
import com.codetest.mytest.dto.StoreReportDto;
import com.codetest.mytest.entity.StoreTransaction;
import com.codetest.mytest.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreControllerTest {

    @Mock
    private StoreService storeService;

    @InjectMocks
    private StoreController storeController;

    @Test
    void givenARequestThenReturnSavedTransactions() throws IOException {
        when(storeService.saveTransactionsByFile(any(MultipartFile.class)))
                .thenReturn(List.of(StoreTransaction.builder().build()));
        InputStream inputStream = TestHelper.getInputStreamFromFile("transaction.txt", this);
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "CNAB.txt",
                MediaType.TEXT_PLAIN_VALUE,
                inputStream.readAllBytes()
        );
        ResponseEntity<?> responseEntity = storeController.saveStoresByFile(file);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void givenARequestThenReturnStoreReport() throws IOException {
        when(storeService.getStoreReport())
                .thenReturn(StoreReportDto.builder().build());
        ResponseEntity<?> responseEntity = storeController.getStoreReport();
        assertNotNull(responseEntity.getBody());
    }
}
