package org.restapis.shoppingcart.mapper;

import org.restapis.shoppingcart.dto.AccountDto;
import org.restapis.shoppingcart.model.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account){

        AccountDto accountDto = new AccountDto(
                account.getId(),account.getIBAN(), account.getBIC(), account.getAccountHolderName(), account.getBankName(), account.getPassword());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto){

        Account account = new Account(
                accountDto.getId(), accountDto.getIBAN(), accountDto.getBIC(), accountDto.getAccountHolderName(), accountDto.getBankName(), accountDto.getPassword());
        return account;
    }

}
