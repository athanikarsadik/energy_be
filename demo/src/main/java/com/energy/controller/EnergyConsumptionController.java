package com.energy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.energy.model.EnergyConsumption;
import com.energy.service.EnergyConsumptionService;

@RestController
@RequestMapping("/api/users")
public class EnergyConsumptionController {

    @Autowired
    private EnergyConsumptionService energyConsumptionService;

    @GetMapping("/monthly/{userId}/{month}/{year}")
    public List<EnergyConsumption> getMonthlyUsage(
            @PathVariable Long userId,
            @PathVariable int month,
            @PathVariable int year) {

        return energyConsumptionService.getUsersByMonth(userId, month, year);
    }

    @GetMapping("/daily/{userId}/{date}")
    public List<EnergyConsumption> getDailyUsage(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return energyConsumptionService.getDailyUsage(userId, date);
    }

    @GetMapping("/weekly/{userId}/{year}/{weekNumber}")
    public List<EnergyConsumption> getWeeklyUsage(
            @PathVariable Long userId,
            @PathVariable int year,
            @PathVariable int weekNumber) {
        return energyConsumptionService.getUsersByWeek(userId, year, weekNumber);
    }

    @PostMapping("/{userId}/consumption")
    public ResponseEntity<EnergyConsumption> createEnergyConsumption(@PathVariable Long userId,
            @RequestBody EnergyConsumption energyConsumption) {
        EnergyConsumption createdConsumption = energyConsumptionService.createEnergyConsumption(userId,
                energyConsumption);
        return new ResponseEntity<>(createdConsumption, HttpStatus.CREATED);
    }

    @PutMapping("/consumption/{energyConsumptionId}")
    public ResponseEntity<EnergyConsumption> updateEnergyConsumption(@PathVariable Long energyConsumptionId,
            @RequestBody EnergyConsumption updatedEnergyConsumption) {
        EnergyConsumption updatedConsumption = energyConsumptionService.updateEnergyConsumption(energyConsumptionId,
                updatedEnergyConsumption);
        return new ResponseEntity<>(updatedConsumption, HttpStatus.OK);
    }

    @DeleteMapping("/consumption/{energyConsumptionId}")
    public ResponseEntity<Void> deleteEnergyConsumption(@PathVariable Long energyConsumptionId) {
        energyConsumptionService.deleteEnergyConsumption(energyConsumptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
