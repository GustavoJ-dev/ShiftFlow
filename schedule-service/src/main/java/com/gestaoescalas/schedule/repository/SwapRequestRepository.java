package com.gestaoescalas.schedule.repository;

import com.gestaoescalas.schedule.entity.SwapRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository responsável pelo acesso e gerenciamento
 * dos dados das solicitações de troca de turnos*/
public interface SwapRequestRepository extends JpaRepository<SwapRequest, Long> {


}
