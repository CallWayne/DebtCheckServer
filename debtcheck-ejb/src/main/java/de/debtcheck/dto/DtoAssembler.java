package de.debtcheck.dto;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.debtcheck.entities.Account;
import de.debtcheck.entities.Claim;
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
  
  public ClaimTO makeDTO(Claim claim) {
	  ClaimTO dto = new ClaimTO();
	  dto.setId(claim.getId());
	  dto.setAmount(claim.getAmount());
	  dto.setOwnerId(claim.getCreditor().getId());
	  return dto;
  }
	
  public List<DebtTO> makeDebtDTO(List<Debt> debts) {
	  ArrayList<DebtTO> dtoList = new ArrayList<>();
	  for (Debt d : debts) {
		  dtoList.add(this.makeDTO(d));
	  }
	  return dtoList;
  }

  public List<ClaimTO> makeClaimDTO(List<Claim> claims) {
	  ArrayList<ClaimTO> dtoList = new ArrayList<>();
	  for (Claim c : claims) {
		  dtoList.add(this.makeDTO(c));
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
