package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.enumes.Degree;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    double amount;
}
