package com.is.infoshareproject.api.mappers;

import com.is.infoshareproject.api.model.Status;
import com.is.infoshareproject.api.model.TransactionInput;
import com.is.infoshareproject.api.model.TransactionOutput;
import com.is.infoshareproject.repository.model.TransactionRecord;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionMapperImpl implements TransactionMapper{

    public TransactionRecord mapInput(TransactionInput input) {
        return TransactionRecord.builder()
                .name(input.getName())
                .creationDate(new Date())
                .userId(input.getUserId())
                .status(Status.VALID)
                .sum(input.getValue())
                .build();
    }

    public TransactionOutput mapJpa(TransactionRecord jpa){
        return TransactionOutput.builder()
                .id(jpa.getId().toString())
                .creationDate(jpa.getCreationDate())
                .value(jpa.getSum())
                .userId(jpa.getUserId())
                .name(jpa.getName())
                .status(jpa.getStatus())
                .build();
    }
}
