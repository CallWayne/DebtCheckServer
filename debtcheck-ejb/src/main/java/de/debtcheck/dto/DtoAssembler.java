package de.debtcheck.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import de.debtcheck.entities.Account;
import de.debtcheck.entities.Debt;

/**
 * 
 * @author Edgar Seibel
 * @æuthor Josua Suren
 *
 */
@Stateless
public class DtoAssembler {

  public DebtTO makeDTO(Debt debt) {
	  DebtTO dto = new DebtTO();
	  dto.setId(debt.getId());
	  dto.setReason(debt.getReason());
	  dto.setAmount(debt.getAmount());
	  dto.setCreditor(debt.getCreditor().getUserName());
	  dto.setDebtor(debt.getDebtor().getUserName());

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
	  dto.setId(account.getId());
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
