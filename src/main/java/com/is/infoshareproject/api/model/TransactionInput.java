package com.is.infoshareproject.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TransactionInput {

	@ApiModelProperty(value = "Name for transaction", example = "Big Bob store - NYC")
	@Size(max = 100)
	private final String name;

	@ApiModelProperty(value = "Id of the user who created transaction", example = "6673jt75djwt7tdr36hd")
	@Size(max = 100)
	private final String userId;

	@ApiModelProperty(value = "Transaction value in USD", example = "55.21")
	@Size(max = 10)
	private final BigDecimal value;

}
