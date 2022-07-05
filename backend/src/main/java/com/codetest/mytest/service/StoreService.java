package com.codetest.mytest.service;

import com.codetest.mytest.dto.StoreReportDto;
import com.codetest.mytest.dto.StoreSummaryDto;
import com.codetest.mytest.entity.StoreTransaction;
import com.codetest.mytest.enums.TransactionSignal;
import com.codetest.mytest.mapper.TransactionMapper;
import com.codetest.mytest.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreService {

    private TransactionMapper transactionMapper;

    private TransactionRepository transactionRepository;

    public List<StoreTransaction> saveTransactionsByFile(MultipartFile multipartFile) throws IOException {
        var file = new String(multipartFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return Arrays.stream(file.split("\n"))
                .map(transactionMapper::toTransaction)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(transactionRepository::save)
                .collect(Collectors.toList());
    }

    public StoreReportDto getStoreReport() {
        List<String> storeList = transactionRepository.getStores();
        List<StoreSummaryDto> storeSummaryDtoList = storeList.stream()
                .map(store -> {
                    List<StoreTransaction> storeTransactionList =
                            transactionRepository.findByStoreNameOrderByEventDateAscTransactionHourAsc(store);
                    return StoreSummaryDto.builder()
                            .name(store)
                            .total(this.getStoreAmount(storeTransactionList))
                            .build();
                })
                .collect(Collectors.toList());
        return StoreReportDto.builder()
                .stores(storeSummaryDtoList)
                .build();
    }

    private BigDecimal getStoreAmount(List<StoreTransaction> storeTransactionList) {
        return storeTransactionList.stream()
                .map(storeTransaction -> {
                    if(storeTransaction.getTransactionType().getTransactionSignal().equals(TransactionSignal.NEGATIVE))
                        return storeTransaction.getAmount().negate();
                    return storeTransaction.getAmount();
                })
                .reduce(new BigDecimal(0), BigDecimal::add);
    }
}
