package com.energy.service;

import com.energy.model.EnergyConsumption;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface EnergyConsumptionService {

    List<EnergyConsumption> getUsersByMonth(Long userId, int month, int year);

    List<EnergyConsumption> getDailyUsage(Long userId, LocalDate date);

    List<EnergyConsumption> getUsersByWeek(Long userId, int year, int weekNumber);
}
