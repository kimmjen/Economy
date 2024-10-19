package hello.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dollar")
@Getter
@Setter
public class Dollar extends BaseEntity {
    // Additional fields and methods specific to Dollar
}