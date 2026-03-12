package com.unasp.StudentAddressApi;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    private String ddd;

    @NotBlank
    private String telefone;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String regiao;
}