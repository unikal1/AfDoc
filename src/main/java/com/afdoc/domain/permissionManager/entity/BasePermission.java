package com.afdoc.domain.permissionManager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BASE_PERMISSION")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false, length = 30)
    private String category;


    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;
}
