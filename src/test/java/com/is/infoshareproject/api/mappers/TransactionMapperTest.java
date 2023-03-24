package com.is.infoshareproject.api.mappers;

import com.is.infoshareproject.api.model.Status;
import com.is.infoshareproject.api.model.TransactionInput;
import com.is.infoshareproject.api.model.TransactionOutput;
import com.is.infoshareproject.repository.model.TransactionRecord;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    @Test
    public void testMapInput() {
        // Create sample TransactionInput object
        TransactionInput input = new TransactionInput(
                "transaction1",
                "user1",
                BigDecimal.valueOf(100)
        );

        // Call the mapInput method
        TransactionMapper mapper = new TransactionMapperImpl();
        TransactionRecord record = mapper.mapInput(input);

        // Check if the fields in the resulting TransactionRecord object are as expected
        assertEquals(input.getName(), record.getName());
        assertEquals(input.getUserId(), record.getUserId());
        assertEquals(Status.VALID, record.getStatus());
        assertEquals(input.getValue(), record.getSum());
    }

    @Test
    public void testMapJpa() {
        // Create sample TransactionRecord object
        TransactionRecord record = new TransactionRecord(
                1L,
                "transaction1",
                "user1",
                BigDecimal.valueOf(100),
                new Date(),
                Status.VALID
        );

        // Call the mapJpa method
        TransactionMapper mapper = new TransactionMapperImpl();
        TransactionOutput output = mapper.mapJpa(record);

        // Check if the fields in the resulting TransactionOutput object are as expected
        assertEquals(record.getId().toString(), output.getId());
        assertEquals(record.getName(), output.getName());
        assertEquals(record.getUserId(), output.getUserId());
        assertEquals(record.getStatus(), output.getStatus());
        assertEquals(record.getSum(), output.getValue());
        assertEquals(record.getCreationDate(), output.getCreationDate());
    }
}