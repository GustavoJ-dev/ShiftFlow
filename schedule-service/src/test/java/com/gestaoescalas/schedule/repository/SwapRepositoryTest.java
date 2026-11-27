
package com.gestaoescalas.schedule.repository;

import com.gestaoescalas.schedule.entity.SwapRequest;
import com.gestaoescalas.schedule.enums.SwapStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class SwapRequestRepositoryTest {

    @Autowired
    private SwapRequestRepository swapRequestRepository;

    @Test
    void shouldSaveSwapRequestSuccessfully() {

        SwapRequest swapRequest = new SwapRequest(1L, 10L, 20L);


        SwapRequest savedRequest = swapRequestRepository.saveAndFlush(swapRequest);


        assertNotNull(savedRequest.getId());
        assertEquals(1L, savedRequest.getShiftId());
        assertEquals(10L, savedRequest.getRequesterEmployeeId());
        assertEquals(20L, savedRequest.getTargetEmployeeId());
        assertEquals(SwapStatus.PENDING, savedRequest.getStatus());
    }

    @Test
    void shouldSetTimestampsWhenSwapRequestIsCreated() {

        SwapRequest swapRequest = new SwapRequest(1L, 10L, 20L);


        SwapRequest savedRequest = swapRequestRepository.saveAndFlush(swapRequest);


        assertNotNull(savedRequest.getCreatedAt());
        assertNotNull(savedRequest.getUpdatedAt());

        assertEquals(savedRequest.getCreatedAt(), savedRequest.getUpdatedAt());
    }


    @Test
    void shouldUpdateUpdatedAtWhenSwapRequestIsUpdated() {

        SwapRequest swapRequest = new SwapRequest(1L, 10L, 20L);

        SwapRequest savedRequest = swapRequestRepository.saveAndFlush(swapRequest);

        LocalDateTime originalCreatedAt = savedRequest.getCreatedAt();
        LocalDateTime originalUpdatedAt = savedRequest.getUpdatedAt();


        savedRequest.setStatus(SwapStatus.APPROVED);

        SwapRequest updatedRequest = swapRequestRepository.saveAndFlush(savedRequest);


        assertEquals(SwapStatus.APPROVED, updatedRequest.getStatus());
        assertEquals(originalCreatedAt, updatedRequest.getCreatedAt());
        assertTrue(updatedRequest.getUpdatedAt().isAfter(originalUpdatedAt));
    }
}
