package com.energy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.energy.model.EnergyConsumption;
import com.energy.model.User;
import com.energy.service.EnergyConsumptionService;
import com.energy.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnergyConsumptionService energyConsumptionService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

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

}