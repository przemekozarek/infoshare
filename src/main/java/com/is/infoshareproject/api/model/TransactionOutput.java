package com.is.infoshareproject.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TransactionOutput {

	@ApiModelProperty(value = "Transaction id", example = "1")
	@Size(max = 10)
	private final String id;

	@ApiModelProperty(value = "Name for transaction", example = "Big Bob store - NYC")
	@Size(max = 100)
	private final String name;

	@ApiModelProperty(value = "Id of the user who created transaction", example = "6673jt75djwt7tdr36hd")
	@Size(max = 100)
	private final String userId;

	@ApiModelProperty(value = "Transaction value in USD", example = "55.21")
	@Size(max = 10)
	private final BigDecimal value;

	@ApiModelProperty(value = "Date of transaction", example = "2023-03-24T10:47:23.155+00:00")
	@Size(max = 10)
	private final Date creationDate;

	@ApiModelProperty(value = "Transaction status", example = "VALID")
	@Size(max = 10)
	private final Status status;



}
