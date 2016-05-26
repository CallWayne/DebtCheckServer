package de.debtcheck.dto;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.debtcheck.entities.account;
import de.debtcheck.entities.debt;


@Stateless
public class DtoAssembler {

  public DebtTO makeDTO(debt debt) {
	  DebtTO dto = new DebtTO();
	  dto.setId(debt.getId());
	  dto.setBalance(debt.getAmount());
	  dto.setOwnerId(debt.getOwner().getId());
	  return dto;
  }
	
  public List<DebtTO> makeDTO(List<debt> accounts) {
	  ArrayList<DebtTO> dtoList = new ArrayList<>();
	  for (debt d : accounts) {
		  dtoList.add(this.makeDTO(d));
	  }
	  return dtoList;
  }

  public AccountTO makeDTO(account account) {
	  AccountTO dto = new AccountTO();
	  dto.setPassword(account.getPassword());
	  dto.setUserName(account.getUserName());
	  return dto;
  }

}
