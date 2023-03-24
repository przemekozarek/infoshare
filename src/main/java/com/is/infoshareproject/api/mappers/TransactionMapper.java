package com.is.infoshareproject.api.mappers;

import com.is.infoshareproject.api.model.TransactionInput;
import com.is.infoshareproject.api.model.TransactionOutput;
import com.is.infoshareproject.repository.model.TransactionRecord;

public interface TransactionMapper {

    TransactionRecord mapInput(TransactionInput input);

    TransactionOutput mapJpa(TransactionRecord jpa);
}
