package com.energy.repository;

import com.energy.model.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, Long> {
     List<EnergyConsumption> findByUser_IdAndTimestampBetween(Long userId, LocalDateTime start, LocalDateTime end);

}