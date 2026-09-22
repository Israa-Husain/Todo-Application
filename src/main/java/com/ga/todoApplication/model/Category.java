package com.ga.todoApplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import lombok.*;
import jakarta.persistence.*;


@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

}
