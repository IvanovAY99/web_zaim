package com.example.web_zaim.controller;

import com.example.web_zaim.service.ContractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContractController {

    private final ContractServiceImpl contractService;

    @GetMapping("/")
    public String paymentsString() {
        return contractService.getThroughLine();
    }
}
