package hello.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dollar")
public class Dollar extends BaseEntity {
    // Additional fields and methods specific to Dollar
}