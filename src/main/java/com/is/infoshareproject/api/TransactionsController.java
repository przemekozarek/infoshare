package com.is.infoshareproject.api;

import com.is.infoshareproject.api.mappers.TransactionMapper;
import com.is.infoshareproject.api.model.PointsSumarize;
import com.is.infoshareproject.api.model.TransactionInput;
import com.is.infoshareproject.api.model.TransactionOutput;
import com.is.infoshareproject.calculations.Connector;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public interface TransactionsController {


    @PostMapping
    @ApiOperation(value = "Create new transaction", notes = "This endpoint allow you to create new transaction for specific client.")
    ResponseEntity<TransactionOutput> createTransaction(@RequestBody TransactionInput transaction);

    @PutMapping(path = "{transactionId}")
    @ApiOperation(value = "Update transaction", notes = "This endpoint allow you to update transaction.")

    ResponseEntity<Object> updateTransaction(@PathVariable("transactionId") Long id,@RequestBody TransactionInput transaction);

    @GetMapping(path = "points/{userId}")
    @ApiOperation(value = "Get points", notes = "This endpoint allow you to get the sum of points collected by specific client.")
    ResponseEntity<Object> getPoints(@PathVariable("userId") Long id) ;
}
