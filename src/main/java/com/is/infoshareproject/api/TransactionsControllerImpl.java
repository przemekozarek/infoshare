package com.is.infoshareproject.api;

import com.is.infoshareproject.api.mappers.TransactionMapper;
import com.is.infoshareproject.api.model.PointsSumarize;
import com.is.infoshareproject.api.model.TransactionInput;
import com.is.infoshareproject.api.model.TransactionOutput;
import com.is.infoshareproject.calculations.Connector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsControllerImpl implements TransactionsController {

    @Autowired
    TransactionMapper mapper;

    @Autowired
    Connector connector;

    private static final Logger logger = LogManager.getLogger(TransactionsControllerImpl.class);

    @PostMapping
    public ResponseEntity<TransactionOutput> createTransaction(@RequestBody TransactionInput transaction) {
        logger.info("createTransaction called");
        return new ResponseEntity<>(mapper.mapJpa(connector.saveTransaction(mapper.mapInput(transaction))), HttpStatus.CREATED);
    }

    @PutMapping(path = "{transactionId}")
    public ResponseEntity<Object> updateTransaction(@PathVariable("transactionId") Long id,@RequestBody TransactionInput transaction) {
        logger.info("updateTransaction called");
        try {
            TransactionOutput response = mapper.mapJpa(connector.editTransaction(id, mapper.mapInput(transaction)));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "points/{userId}")
    public ResponseEntity<Object> getPoints(@PathVariable("userId") Long id) {
        logger.info("getPoints called");
        try {
            PointsSumarize response = PointsSumarize.builder()
                    .sumOfPoints(connector.getPoints(id))
                    .userId(id.toString())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(ChangeSetPersister.NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }
}
