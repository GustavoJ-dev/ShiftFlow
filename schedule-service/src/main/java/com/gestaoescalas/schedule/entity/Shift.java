package com.gestaoescalas.schedule.entity;

import com.gestaoescalas.schedule.enums.ShiftStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Representa um turno de trabalho, armazenando o colaborador
 * responsável, o período agendado e seu estado atual.
 */
@Entity
@Table(name = "shifts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Identificador do colaborador responsável pelo turno. */
    @Column(nullable = false)
    private Long employeeId;

    /** Data e horário de início do turno. */
    @Column(nullable = false)
    private LocalDateTime startAt;

    /** Data e horário de término do turno. */
    @Column(nullable = false)
    private LocalDateTime endAt;

    /** Estado atual do turno, inicialmente definido como ativo. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ShiftStatus status = ShiftStatus.ACTIVE;

    /** Data e horário em que o turno foi criado. */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Data e horário da última atualização do turno. */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Shift(Long employeeId, LocalDateTime startAt, LocalDateTime endAt){

        this.employeeId = employeeId;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    /**
     * Define as datas de criação e atualização antes da primeira
     * persistência da entidade.
     */
    @PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }

    /**
     * Atualiza a data de modificação sempre que um turno
     * já persistido sofrer alguma alteração.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
