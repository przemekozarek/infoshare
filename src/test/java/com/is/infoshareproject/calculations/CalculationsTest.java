package com.is.infoshareproject.calculations;

import com.is.infoshareproject.api.model.Status;
import com.is.infoshareproject.repository.model.TransactionRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {



    @Test
    public void testSumPoints() {
        // Create sample TransactionRecord objects
        TransactionRecord record1 = new TransactionRecord(
                1L,
                "transaction1",
                "user1",
                BigDecimal.valueOf(100),
                Date.from(Instant.now()),
                Status.VALID
        );

        TransactionRecord record2 = new TransactionRecord(
                2L,
                "transaction2",
                "user1",
                BigDecimal.valueOf(200),
                Date.from(Instant.now().minusSeconds(60L * 60 * 24 * 90)), // 3 months ago
                Status.VALID
        );

        TransactionRecord record3 = new TransactionRecord(
                3L,
                "transaction3",
                "user1",
                BigDecimal.valueOf(123.9),
                Date.from(Instant.now().minusSeconds(60 * 24 * 90)), // 3 months ago
                Status.VALID
        );

        // Create a list containing the sample TransactionRecord objects
        List<TransactionRecord> list = Arrays.asList(record1, record2, record3);

        // Call the sumPoints method
        Calculations calculations = new Calculations();
        int result = calculations.sumPoints(list);

        // Check if the result is as expected
        // Based on your rules, add the expected result for the provided TransactionRecord objects
        int expectedResult = 146;
        assertEquals(expectedResult, result);
    }
}