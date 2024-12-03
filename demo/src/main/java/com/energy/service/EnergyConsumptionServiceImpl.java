
package com.energy.service;

import com.energy.model.EnergyConsumption;
import com.energy.repository.EnergyConsumptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EnergyConsumptionServiceImpl implements EnergyConsumptionService {

    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EnergyConsumption> getUsersByMonth(Long userId, int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDateTime start = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime end = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        List<EnergyConsumption> consumptions = energyConsumptionRepository.findByUser_IdAndTimestampBetween(userId,
                start, end);
        return consumptions != null ? consumptions : Collections.emptyList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<EnergyConsumption> getUsersByWeek(Long userId, int year, int weekNumber) {
        LocalDate startDate = LocalDate.now()
                .with(WeekFields.of(Locale.getDefault()).weekOfYear(), weekNumber)

                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        LocalDate endDate = startDate.plusDays(6);

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        List<EnergyConsumption> consumptions = energyConsumptionRepository.findByUser_IdAndTimestampBetween(userId,
                startDateTime, endDateTime);
        return consumptions != null ? consumptions : Collections.emptyList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnergyConsumption> getDailyUsage(Long userId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        List<EnergyConsumption> consumptions = energyConsumptionRepository.findByUser_IdAndTimestampBetween(userId,
                startOfDay, endOfDay);

        return consumptions != null ? consumptions : Collections.emptyList();

    }

}
