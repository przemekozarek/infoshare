package com.is.infoshareproject.calculations;

import com.is.infoshareproject.api.model.Status;
import com.is.infoshareproject.repository.TransactionRepository;
import com.is.infoshareproject.repository.model.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Calculations {
    //Configuration oof rules
    final Map<Integer, Double> rules = Map.of(2, 100.00, 1, 50.00);


    public int sumPoints(List<TransactionRecord> list){
        List<TransactionRecord> filteredList = prepareTransactionLists(list);
        int overallPoints = 0;
        for (TransactionRecord e : filteredList) {
            overallPoints = overallPoints + sumPointsForTransaction(e.getSum().doubleValue());
        }
        return overallPoints;
    }

    private int sumPointsForTransaction(double sum) {
        int points = 0;
        List<Map.Entry<Integer, Double>> entryList = new ArrayList<>(rules.entrySet());
        Collections.sort(entryList, Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for (Map.Entry<Integer, Double> t : entryList) {
            if (sum >= t.getValue()) {
                int pointedSum = (int) (sum - t.getValue());
                points = points + (pointedSum * t.getKey());
                sum = sum - pointedSum;
            }
        }
        return points;
    }

    private List<TransactionRecord> prepareTransactionLists(List<TransactionRecord>  list) {
        return list.stream()
                .filter(a -> {
                    LocalDate currentDate = LocalDate.now();
                    LocalDate threeMonthsAgo = currentDate.minus(3, ChronoUnit.MONTHS);
                    LocalDate creationDate = a.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    return creationDate.isAfter(threeMonthsAgo) && a.getStatus().name().equals(Status.VALID.name());
                })
                .collect(Collectors.toList());
    }

}
