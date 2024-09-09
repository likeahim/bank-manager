package com.likeahim.bank.account.manager.controller;

import com.likeahim.bank.account.manager.AccountMapper;
import com.likeahim.bank.account.manager.domain.account.Account;
import com.likeahim.bank.account.manager.domain.dto.AccountDto;
import com.likeahim.bank.account.manager.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bank/manager/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        System.out.println(accountDto.getAccountType() + " that account type");
        Account account = accountService.save(accountMapper.mapToEntity(accountDto));
        return ResponseEntity.ok(accountMapper.mapToDto(account));
    }

    @SneakyThrows
    @GetMapping(value = "{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long accountId) {
        Account account = accountService.findById(accountId);
        return ResponseEntity.ok(accountMapper.mapToDto(account));
    }
}
