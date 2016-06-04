package de.debtcheck.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import de.debtcheck.entities.Account;
import de.debtcheck.entities.Debt;


@Stateless
public class DtoAssembler {

  public DebtTO makeDTO(Debt debt) {
	  DebtTO dto = new DebtTO();
	  dto.setId(debt.getId());
	  dto.setAmount(debt.getAmount());
	  dto.setOwnerId(debt.getDebtor().getId());
	  return dto;
  }
  
  public List<DebtTO> makeDebtDTO(List<Debt> debts) {
	  ArrayList<DebtTO> dtoList = new ArrayList<>();
	  for (Debt d : debts) {
		  dtoList.add(this.makeDTO(d));
	  }
	  return dtoList;
  }

  
  public AccountTO makeDTO(Account account) {
	  AccountTO dto = new AccountTO();
	  dto.setUserName(account.getUserName());
	  dto.setPassword(account.getPassword());
	  dto.setEmail(account.getEmail());  
	  return dto;
  }
  
  public Set<AccountTO> makeAccountDTO(Set<Account> accounts) {
	  Set<AccountTO> dtoList = new HashSet<>();
	  for (Account a : accounts) {
		  dtoList.add(this.makeDTO(a));
	  }
	  return dtoList;
  }

}
