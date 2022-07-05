package com.codetest.mytest.controller;

import com.codetest.mytest.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/transactions")
    public ResponseEntity<?> saveStoresByFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(storeService.saveTransactionsByFile(multipartFile));
    }

    @GetMapping("/store-report")
    public ResponseEntity<?> getStoreReport() throws IOException {
        return ResponseEntity.ok(storeService.getStoreReport());
    }
}
