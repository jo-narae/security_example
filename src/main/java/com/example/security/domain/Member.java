package com.example.security.domain;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "oAuth2Id", "email", "password", "nickname"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "oauth2_id", unique = true)
    private String oAuth2Id;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String nickname;
}