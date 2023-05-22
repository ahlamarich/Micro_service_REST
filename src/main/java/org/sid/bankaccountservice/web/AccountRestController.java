package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.entitises.Bankaccount;
import org.sid.bankaccountservice.repositorises.BankAccountRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {

    private BankAccountRepositories bankAccountRepositories;

    public AccountRestController(BankAccountRepositories bankAccountRepositories) {
        this.bankAccountRepositories = bankAccountRepositories;
    }

    @GetMapping("/Bankaccounts")
    public List<Bankaccount> Bankaccounts(){
        return bankAccountRepositories.findAll();
    }

    @GetMapping("Bankaccounts/{id}")
    public Bankaccount Bankaccount(@PathVariable String id){
        return bankAccountRepositories.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)) );
    }


    @PostMapping("/Bankaccounts")
    public Bankaccount save(@RequestBody Bankaccount bankaccount){
        if(bankaccount.getId()==null)bankaccount.setId(UUID.randomUUID().toString());
        return bankAccountRepositories.save(bankaccount);
    }


    @PutMapping("/Bankaccounts/{id}")
    public Bankaccount Update(@PathVariable String id,@RequestBody Bankaccount bankaccount){
        Bankaccount account=bankAccountRepositories.findById(id).orElseThrow();
        if(bankaccount.getBalance()!=null) account.setBalance(bankaccount.getBalance());
        if(bankaccount.getType()!=null)account.setType(bankaccount.getType());
        if(bankaccount.getCreatAt()!=null) account.setCreatAt(new Date());
        if(bankaccount.getCurrency()!=null) account.setCurrency(bankaccount.getCurrency());
        return bankAccountRepositories.save(account);
    }

    @DeleteMapping ("/Bankaccounts/{id}")
    public void delete(@PathVariable String id){
         bankAccountRepositories.deleteById(id);
    }
}

