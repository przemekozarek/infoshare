package com.is.infoshareproject.repository;

import com.is.infoshareproject.repository.model.TransactionRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionRecord, Long> {
    List<TransactionRecord> findAllByUserId(String userId);
}