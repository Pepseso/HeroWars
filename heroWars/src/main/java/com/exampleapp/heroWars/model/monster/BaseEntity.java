package com.exampleapp.heroWars.model.monster;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
