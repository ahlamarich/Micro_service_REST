package org.sid.bankaccountservice.repositorises;

import org.sid.bankaccountservice.entitises.Bankaccount;
import org.springframework.data.jpa.repository.JpaRepository;




public interface BankAccountRepositories extends JpaRepository<Bankaccount,String> {
}
