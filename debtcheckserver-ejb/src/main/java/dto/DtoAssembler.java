package dto;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import account.Account;
import debt.Debt;


@Stateless
public class DtoAssembler {

  public DebtTO makeDTO(Debt debt) {
	  DebtTO dto = new DebtTO();
	  dto.setId(debt.getId());
	  dto.setBalance(debt.getAmount());
	  dto.setOwnerId(debt.getOwner().getId());
	  return dto;
  }
	
  public List<DebtTO> makeDTO(List<Debt> accounts) {
	  ArrayList<DebtTO> dtoList = new ArrayList<>();
	  for (Debt d : accounts) {
		  dtoList.add(this.makeDTO(d));
	  }
	  return dtoList;
  }

  public AccountTO makeDTO(Account account) {
	  AccountTO dto = new AccountTO();
	  dto.setPassword(account.getPassword());
	  dto.setUserName(account.getUserName());
	  return dto;
  }

}
