package com.wallet.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletDTO {

	private Long id;
	@Length(min = 3, message = "O nome dever ter no mínimo 3 caracteres")
	@NotNull(message = "O nome nãoo pode ser nulo")
	private String name;
	@NotNull(message = "Insira um valor para a carteira")
	private BigDecimal value;
}
