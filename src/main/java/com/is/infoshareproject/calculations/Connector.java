package com.is.infoshareproject.calculations;

import com.is.infoshareproject.repository.TransactionRepository;
import com.is.infoshareproject.repository.model.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Connector {

    @Autowired
    TransactionRepository repository;

    @Autowired
    Calculations calc;

    public TransactionRecord saveTransaction(TransactionRecord jpa){
        return repository.save(jpa);
    }

    public TransactionRecord editTransaction(Long transactionId, TransactionRecord jpa) throws ChangeSetPersister.NotFoundException {
        TransactionRecord oldOne = repository.findById(transactionId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        jpa.setId(oldOne.getId());
        return repository.save(jpa);
    }

    public int getPoints(Long userId) throws ChangeSetPersister.NotFoundException {
        List<TransactionRecord> transactionRecords = repository.findAllByUserId(userId.toString());
        if (transactionRecords==null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        int sum = calc.sumPoints(transactionRecords);
        return sum;
    }

}
