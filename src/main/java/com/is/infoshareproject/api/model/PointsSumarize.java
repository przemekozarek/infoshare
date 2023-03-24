package com.is.infoshareproject.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PointsSumarize {

	private final String userId;
	private final int sumOfPoints;



}
