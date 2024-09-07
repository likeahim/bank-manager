package com.likeahim.bank.account.manager.controller;

import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bank/manager/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.save(account));
    }

    @SneakyThrows
    @GetMapping(value = "{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.findById(accountId));
    }
}
