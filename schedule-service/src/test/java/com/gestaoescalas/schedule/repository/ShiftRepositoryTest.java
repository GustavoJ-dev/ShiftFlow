package com.gestaoescalas.schedule.repository;

import com.gestaoescalas.schedule.entity.Shift;
import com.gestaoescalas.schedule.enums.ShiftStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ShiftRepositoryTest {

    @Autowired
    private ShiftRepository shiftRepository;

    private final Long employeeId = 1L;

    @Test
    void shouldReturnTrueWhenShiftOverlaps(){

        Shift existingShift = new Shift(
                employeeId,
                LocalDateTime.of(2026, 12, 10, 8, 0),
                LocalDateTime.of(2026, 12, 10, 12, 0)
        );

        shiftRepository.saveAndFlush(existingShift);

        boolean hasConflict = shiftRepository.existsByEmployeeIdAndStatusAndStartAtLessThanAndEndAtGreaterThan(
                employeeId,
                ShiftStatus.ACTIVE,
                LocalDateTime.of(2026, 12, 10, 14, 0),
                LocalDateTime.of(2026, 12, 10, 10, 0)
        );

        assertTrue(hasConflict);
    }

    @Test
    void shouldReturnFalseWhenShiftsAreConsecutive() {

        Shift existingShift = new Shift(
                employeeId,
                LocalDateTime.of(2026, 12, 10, 8, 0),
                LocalDateTime.of(2026, 12, 10, 12, 0)
        );

        shiftRepository.saveAndFlush(existingShift);


        boolean hasConflict = shiftRepository
                .existsByEmployeeIdAndStatusAndStartAtLessThanAndEndAtGreaterThan(
                        employeeId,
                        ShiftStatus.ACTIVE,
                        LocalDateTime.of(2026, 12, 10, 16, 0),
                        LocalDateTime.of(2026, 12, 10, 12, 0)
                );

        assertFalse(hasConflict);
    }

    @Test
    void shouldReturnFalseWhenExistingShiftIsCancelled() {

        Shift existingShift = new Shift(
                employeeId,
                LocalDateTime.of(2026, 12, 10, 8, 0),
                LocalDateTime.of(2026, 12, 10, 12, 0)
        );

        existingShift.setStatus(ShiftStatus.CANCELLED);

        shiftRepository.saveAndFlush(existingShift);


        boolean hasConflict = shiftRepository
                .existsByEmployeeIdAndStatusAndStartAtLessThanAndEndAtGreaterThan(
                        employeeId,
                        ShiftStatus.ACTIVE,
                        LocalDateTime.of(2026, 12, 10, 14, 0),
                        LocalDateTime.of(2026, 12, 10, 10, 0)
                );

        assertFalse(hasConflict);
    }

    @Test
    void shouldUpdateUpdatedAtWhenShiftIsUpdated() {

        Shift shift = new Shift(
                1L,
                LocalDateTime.of(2026, 12, 10, 8, 0),
                LocalDateTime.of(2026, 12, 10, 12, 0)
        );

        Shift savedShift = shiftRepository.saveAndFlush(shift);

        LocalDateTime originalCreatedAt = savedShift.getCreatedAt();
        LocalDateTime originalUpdatedAt = savedShift.getUpdatedAt();


        savedShift.setStatus(ShiftStatus.CANCELLED);

        Shift updatedShift = shiftRepository.saveAndFlush(savedShift);

        assertEquals(ShiftStatus.CANCELLED, updatedShift.getStatus());
        assertEquals(originalCreatedAt, updatedShift.getCreatedAt());
        assertTrue(updatedShift.getUpdatedAt().isAfter(originalUpdatedAt));
    }

}
