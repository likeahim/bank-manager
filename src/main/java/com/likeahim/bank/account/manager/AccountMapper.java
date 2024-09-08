package com.likeahim.bank.account.manager;

import com.likeahim.bank.account.manager.domain.account.*;
import com.likeahim.bank.account.manager.domain.dto.*;
import com.likeahim.bank.account.manager.domain.transaction.AccountTransaction;
import com.likeahim.bank.account.manager.exception.AccountDoesNotExistException;
import com.likeahim.bank.account.manager.exception.CustomerNotFoundException;
import com.likeahim.bank.account.manager.exception.NoSuchAccountTypeException;
import com.likeahim.bank.account.manager.exception.UnknownTransactionType;
import com.likeahim.bank.account.manager.repository.CustomerRepository;
import com.likeahim.bank.account.manager.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountMapper {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public Account mapToEntity(AccountDto accountDto) throws AccountDoesNotExistException, CustomerNotFoundException, NoSuchAccountTypeException {
        switch (AccountType.fromValue(accountDto.getAccountType())) {
            case BUSINESS: return mapToBusinessAccount(accountDto);
            case CREDIT: return mapToCreditAccount((CreditAccountDto) accountDto);
            case OFFICIAL: return mapToOfficialAccount(accountDto);
            case REGULAR: return mapToRegularAccount((RegularAccountDto) accountDto);
            case SAVING: return mapToSavingAccount((SavingAccountDto) accountDto);
            default: throw new AccountDoesNotExistException();
        }
    }

    public AccountDto mapToDto(Account account) {
        switch (account.getAccountType()) {
            case BUSINESS: return mapToBusinessDto((BusinessAccount) account);
            case CREDIT: return mapToCreditDto((CreditAccount) account);
            case OFFICIAL: return mapToOfficialDto((OfficialAccount) account);
            case REGULAR: return mapToRegularDto((RegularAccount) account);
            case SAVING: return mapToSavingDto((SavingAccount) account);
            default: throw new UnknownTransactionType();
        }
    }

    private AccountDto mapToSavingDto(SavingAccount account) {
        return null;
    }

    private AccountDto mapToRegularDto(RegularAccount account) {
        return null;
    }

    private AccountDto mapToOfficialDto(OfficialAccount account) {
        return null;
    }

    private CreditAccountDto mapToCreditDto(CreditAccount account) {
        return new CreditAccountDto(
                account.getId(),
                account.getCustomer().getId(),
                account.getFunds(),
                account.getCashOutLimit(),
                account.getCreated(),
                account.getAccountType().getValue(),
                account.getMonthlyFee(),
                account.getTransactions().stream()
                        .map(AccountTransaction::getId)
                        .toList(),
                account.getCreditAmount(),
                account.getPercentage(),
                account.getWithdrawnFunds(),
                account.getToRepaid()
        );
    }

    private BusinessAccountDto mapToBusinessDto(BusinessAccount account) {
        return new BusinessAccountDto(
                account.getId(),
                account.getCustomer().getId(),
                account.getFunds(),
                account.getCashOutLimit(),
                account.getCreated(),
                account.getAccountType().getValue(),
                account.getMonthlyFee(),
                account.getTransactions().stream()
                        .map(t -> t.getId())
                        .toList()
                );
    }

    private SavingAccount mapToSavingAccount(SavingAccountDto accountDto) throws CustomerNotFoundException, NoSuchAccountTypeException {
        return SavingAccount.builder()
                .id(accountDto.getId())
                .customer(customerRepository.findById(accountDto.getCustomerId()).orElseThrow(CustomerNotFoundException::new))
                .funds(accountDto.getFunds())
                .cashOutLimit(accountDto.getCashOutLimit() != null ? accountDto.getCashOutLimit() : null)
                .created(accountDto.getCreated())
                .accountType(AccountType.fromValue(accountDto.getAccountType()))
                .monthlyFee(accountDto.getMonthlyFee())
                .transactions(accountDto.getTransactions().stream()
                        .map(t -> transactionRepository.findById(t).orElseThrow(UnknownTransactionType::new))
                        .toList())
                .interest(accountDto.getInterest())
                .editable(accountDto.isEditable())
                .expireDate(accountDto.getExpireDate())
                .build();
    }

    private RegularAccount mapToRegularAccount(RegularAccountDto accountDto) throws CustomerNotFoundException, NoSuchAccountTypeException {
        return RegularAccount.builder()
                .id(accountDto.getId())
                .customer(customerRepository.findById(accountDto.getCustomerId()).orElseThrow(CustomerNotFoundException::new))
                .funds(accountDto.getFunds())
                .cashOutLimit(accountDto.getCashOutLimit() != null ? accountDto.getCashOutLimit() : null)
                .created(accountDto.getCreated())
                .accountType(AccountType.fromValue(accountDto.getAccountType()))
                .monthlyFee(accountDto.getMonthlyFee())
                .transactions(accountDto.getTransactions().stream()
                        .map(t -> transactionRepository.findById(t).orElseThrow(UnknownTransactionType::new))
                        .toList())
                .blik(accountDto.getBlik())
                .build();
    }

    private OfficialAccount mapToOfficialAccount(AccountDto accountDto) throws CustomerNotFoundException, NoSuchAccountTypeException {
        return OfficialAccount.builder()
                .id(accountDto.getId())
                .customer(customerRepository.findById(accountDto.getCustomerId()).orElseThrow(CustomerNotFoundException::new))
                .funds(accountDto.getFunds())
                .cashOutLimit(accountDto.getCashOutLimit() != null ? accountDto.getCashOutLimit() : null)
                .created(accountDto.getCreated())
                .accountType(AccountType.fromValue(accountDto.getAccountType()))
                .monthlyFee(accountDto.getMonthlyFee())
                .transactions(accountDto.getTransactions().stream()
                        .map(t -> transactionRepository.findById(t).orElseThrow(UnknownTransactionType::new))
                        .toList())
                .build();
    }

    private CreditAccount mapToCreditAccount(CreditAccountDto accountDto) throws CustomerNotFoundException, NoSuchAccountTypeException {
        return CreditAccount.builder()
                .id(accountDto.getId())
                .customer(customerRepository.findById(accountDto.getCustomerId()).orElseThrow(CustomerNotFoundException::new))
                .funds(accountDto.getFunds())
                .cashOutLimit(accountDto.getCashOutLimit() != null ? accountDto.getCashOutLimit() : null)
                .created(accountDto.getCreated())
                .accountType(AccountType.fromValue(accountDto.getAccountType()))
                .monthlyFee(accountDto.getMonthlyFee())
                .transactions(accountDto.getTransactions().stream()
                        .map(t -> transactionRepository.findById(t).orElseThrow(UnknownTransactionType::new))
                        .toList())
                .creditAmount(accountDto.getCreditAmount())
                .percentage(accountDto.getPercentage())
                .withdrawnFunds(accountDto.getWithdrawnFunds())
                .toRepaid(accountDto.getToRepaid())
                .build();
    }

    private BusinessAccount mapToBusinessAccount(AccountDto accountDto) throws CustomerNotFoundException, NoSuchAccountTypeException {
        return BusinessAccount.builder()
                .id(accountDto.getId())
                .customer(customerRepository.findById(accountDto.getCustomerId()).orElseThrow(CustomerNotFoundException::new))
                .funds(accountDto.getFunds())
                .cashOutLimit(accountDto.getCashOutLimit() != null ? accountDto.getCashOutLimit() : null)
                .created(accountDto.getCreated())
                .accountType(AccountType.fromValue(accountDto.getAccountType()))
                .monthlyFee(accountDto.getMonthlyFee())
                .transactions(accountDto.getTransactions().stream()
                        .map(t -> transactionRepository.findById(t).orElseThrow(UnknownTransactionType::new))
                        .toList())
                .build();
    }
}
