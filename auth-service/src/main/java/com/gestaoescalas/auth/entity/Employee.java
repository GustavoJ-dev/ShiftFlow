package com.gestaoescalas.auth.entity;

import com.gestaoescalas.auth.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representa um colaborador cadastrado no sistema,
 * armazenando seus dados pessoais, credenciais de acesso,
 * papel de autorização e status de atividade.
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(nullable = false)
    private boolean active;

    /**
     * Data e hora de criação do registro.
     * Seu valor não pode ser alterado após a persistência inicial.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Construtor utilizado para criar um novo colaborador,
     * inicializando seus dados pessoais, credenciais,
     * papel de autorização e status de atividade.
     *
     * @param name nome do colaborador
     * @param email endereço de e-mail do colaborador
     * @param password senha do colaborador
     * @param role papel de autorização do colaborador
     * @param active indica se o colaborador está ativo
     */
    public Employee(String name, String email, String password, Role role, boolean active){

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    /**
     * Define automaticamente as datas de criação e atualização
     * antes da primeira persistência da entidade.
     */
    @PrePersist
    protected void onCreate(){

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }

    /**
     * Atualiza automaticamente a data da última modificação
     * antes da atualização de uma entidade já persistida.
     */
    @PreUpdate
    protected void onUpdate(){

        this.updatedAt = LocalDateTime.now();
    }
}
