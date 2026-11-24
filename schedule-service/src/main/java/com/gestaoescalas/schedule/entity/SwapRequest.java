package com.gestaoescalas.schedule.entity;

import com.gestaoescalas.schedule.enums.SwapStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representa uma solicitação de troca de turno entre colaboradores,
 * armazenando os envolvidos, o turno solicitado e o estado da solicitação.
 */
@Entity
@Table(name = "swap_requests")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SwapRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Identificador do turno relacionado à solicitação de troca. */
    @Column(nullable = false)
    private Long shiftId;

    /** Identificador do colaborador que solicitou a troca. */
    @Column(nullable = false)
    private Long requesterEmployeeId;

    /** Identificador do colaborador que receberá o turno, caso aprovado. */
    @Column(nullable = false)
    private Long targetEmployeeId;

    /** Estado atual da solicitação, inicialmente definido como pendente. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SwapStatus status = SwapStatus.PENDING;

    /** Data e horário em que a solicitação foi criada. */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Data e horário da última atualização da solicitação. */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Define as datas de criação e atualização antes da primeira
     * persistência da entidade.
     */
    @PrePersist
    protected void onCreate() {

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Atualiza a data de modificação sempre que uma solicitação
     * já persistida sofrer alguma alteração.
     */
    @PreUpdate
    protected void onUpdate() {

        this.updatedAt = LocalDateTime.now();
    }
}
