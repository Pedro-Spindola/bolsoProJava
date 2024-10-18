/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;
import util.Constraints;
/**
 * @author Pedro Spindola
 * @date 13/10/2024
 * @brief Class Usuario
 */
public class Usuario {

    private Integer id = 0;
    private String nome = "";
    private String email = "";
    private String senha = "";

    public Usuario(String email, String senha, String nome) {
        if(Constraints.EmailValido(email))this.email = email;
        else throw new IllegalArgumentException("Email inválido!");
        if(Constraints.SenhaValida(senha))this.senha = senha;
        else throw new IllegalArgumentException("Senha inválida, deverar conter no mínimo 8 dígitos.");
        if(!"".equals(nome))this.nome = nome;
        else throw new IllegalArgumentException("Nome de usuário não pode ser vazio.");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
