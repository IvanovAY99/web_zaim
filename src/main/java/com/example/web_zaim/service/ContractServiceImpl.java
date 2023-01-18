package com.example.web_zaim.service;

import com.example.web_zaim.entity.Contract;
import com.example.web_zaim.model.enums.RatingPayments;
import com.example.web_zaim.repository.ContractRepository;
import liquibase.repackaged.org.apache.commons.lang3.time.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public String getThroughLine() {
        List<Contract> contractList = contractRepository.findAll();
        Map<Date, Character> dateCharHashMap = new TreeMap<>();
        List<Date> fullDates = new ArrayList<>();
        for (Contract contract : contractList
        ) {
            fullDates.add(contract.getFirstPaymentsDate());
            fullDates.add(DateUtils.addMonths(contract.getFirstPaymentsDate(), contract.getPayments().length() - 1));
        }
        Date min = Collections.min(fullDates);
        Date max = Collections.max(fullDates);

        while (min.getMonth() <= max.getMonth() && min.getYear() == max.getYear()) {
            dateCharHashMap.put(min, 'X');
            min = DateUtils.addMonths(min, 1);
        }

        for (Contract contract : contractList
        ) {
            var contractStartDate = contract.getFirstPaymentsDate();
            for (Character charPayment : contract.getPayments().toCharArray()
            ) {
                for (Date date : dateCharHashMap.keySet()
                ) {
                    if (date.getMonth() == contractStartDate.getMonth() &&
                            date.getYear() == contractStartDate.getYear()) {
                        var charMonth = dateCharHashMap.get(date);
                        if (RatingPayments.getValueByCharacter(charMonth).getValue()
                                < RatingPayments.getValueByCharacter(charPayment).getValue()) {
                            dateCharHashMap.put(date, charPayment);
                        } else {
                            dateCharHashMap.put(date, charMonth);
                        }
                    }
                }
                contractStartDate = DateUtils.addMonths(contractStartDate, 1);
            }
        }
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM");
        log.info(dateCharHashMap.values().toString());
        StringBuilder resultString = new StringBuilder();
        resultString.append('\n');
        dateCharHashMap.forEach(
                (k,v) -> resultString.append(formatForDateNow.format(k)).append(' ')
        );
        resultString.append('\n');
        dateCharHashMap.forEach(
                (k,v) -> resultString.append(v.toString()).append('\t').append('\t')
        );
        log.info(String.valueOf(resultString));

        return dateCharHashMap.values().toString();
    }
}
