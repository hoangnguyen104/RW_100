package com.vti.entity;

import javax.persistence.*;

import com.vti.entity.enums.PositionName;
import lombok.*;

@Entity
@Table(name = "position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "position_name", nullable = false, unique = true)
    private PositionName positionName;

}
