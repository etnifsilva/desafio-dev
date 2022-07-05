package com.codetest.mytest.controller;

import com.codetest.mytest.dto.StoreReportDto;
import com.codetest.mytest.entity.StoreTransaction;
import com.codetest.mytest.service.StoreService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @Operation(description = "Serviço responsável por salvar as transações de lojas através de upload de arquivo")
    @PostMapping(value = "/transactions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<List<StoreTransaction>> saveStoresByFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(storeService.saveTransactionsByFile(multipartFile));
    }

    @Operation(description = "Serviço de listagem de transações sumarizados por loja")
    @GetMapping(value = "/store-report", produces = "application/json")
    public ResponseEntity<StoreReportDto> getStoreReport() throws IOException {
        return ResponseEntity.ok(storeService.getStoreReport());
    }
}
