package models.roles;

import models.Usuario;

import java.util.Date;

public class Caixa extends Usuario {
    public Caixa(int id, String nome, long cpf, char sexo, Date dataNasc, String telefone1, String telefone2, long cep, String num, String rua, String comp, String bairro, String cidade, String estado, String login, String senha) {
        super(id, nome, cpf, sexo, dataNasc, telefone1, telefone2, cep, num, rua, comp, bairro, cidade, estado, login, senha);
    }
}
