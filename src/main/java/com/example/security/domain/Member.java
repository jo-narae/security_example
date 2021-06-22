package com.example.security.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@Table(name = "member")
@NoArgsConstructor
@ToString(of = {"id", "socialId", "email", "password", "nickname"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "social_id", unique = true)
    private String socialId;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;
}