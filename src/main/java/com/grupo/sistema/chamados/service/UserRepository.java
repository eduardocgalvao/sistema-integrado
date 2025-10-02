package com.grupo.sistema.chamados.service;

import com.grupo.sistema.chamados.model.User;

import java.util.List;

public interface UserRepository {
    public abstract void CadastrarUsuario(User user);
    List<User> searchUser();
    public abstract void editUser(User user);
    public abstract void deleteUser(User user);



}
