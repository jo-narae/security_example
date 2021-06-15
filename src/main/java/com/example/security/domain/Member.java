package com.example.security.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    private String password;

    @Column(name = "employee_no", length = 10)
    private String employeeNo;

    private String name;

    private String position;

    private String telephone;

    private String depart;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private int level;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}