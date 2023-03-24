package com.is.infoshareproject.calculations;

import com.is.infoshareproject.api.model.Status;
import com.is.infoshareproject.repository.TransactionRepository;
import com.is.infoshareproject.repository.model.TransactionRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConnectorTest {

    @InjectMocks
    private Connector connector;

    @Mock
    private TransactionRepository repository;

    @Mock
    private Calculations calc;

    @Test
    public void testSaveTransaction() {
        TransactionRecord record = new TransactionRecord(
                1L,
                "transaction1",
                "user1",
                BigDecimal.valueOf(100),
                new Date(),
                Status.VALID
        );

        when(repository.save(record)).thenReturn(record);

        TransactionRecord result = connector.saveTransaction(record);

        assertEquals(record, result);
        verify(repository).save(record);
    }

    @Test
    public void testEditTransaction() throws ChangeSetPersister.NotFoundException {
        TransactionRecord oldRecord = new TransactionRecord(
                1L,
                "transaction1",
                "user1",
                BigDecimal.valueOf(100),
                new Date(),
                Status.VALID
        );

        TransactionRecord newRecord = new TransactionRecord(
                null,
                "transaction2",
                "user1",
                BigDecimal.valueOf(200),
                new Date(),
                Status.VALID
        );

        when(repository.findById(1L)).thenReturn(Optional.of(oldRecord));
        when(repository.save(newRecord)).thenReturn(newRecord);

        TransactionRecord result = connector.editTransaction(1L, newRecord);

        assertEquals(newRecord, result);
        verify(repository).findById(1L);
        verify(repository).save(newRecord);
    }

    @Test
    public void testGetPoints() throws ChangeSetPersister.NotFoundException {
        List<TransactionRecord> transactionRecords = Arrays.asList(
                new TransactionRecord(1L, "transaction1", "user1", BigDecimal.valueOf(100), new Date(), Status.VALID),
                new TransactionRecord(2L, "transaction2", "user1", BigDecimal.valueOf(200), new Date(), Status.VALID)
        );

        when(repository.findAllByUserId("1")).thenReturn(transactionRecords);
        when(calc.sumPoints(transactionRecords)).thenReturn(300);

        int result = connector.getPoints(1L);

        assertEquals(300, result);
        verify(repository).findAllByUserId("1");
        verify(calc).sumPoints(transactionRecords);
    }

    @Test
    public void testEditTransaction_NotFoundException() {
        TransactionRecord newRecord = new TransactionRecord(
                null,
                "transaction2",
                "user1",
                BigDecimal.valueOf(200),
                new Date(),
                Status.VALID
        );

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> connector.editTransaction(1L, newRecord));

        verify(repository).findById(1L);
        verify(repository, never()).save(any(TransactionRecord.class));
    }

    @Test
    public void testGetPoints_NotFoundException() {
        when(repository.findAllByUserId("1")).thenReturn(null);

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> connector.getPoints(1L));

        verify(repository).findAllByUserId("1");
        verify(calc, never()).sumPoints(anyList());
    }

}
