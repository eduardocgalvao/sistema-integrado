package com.grupo.sistema.chamados.service;


import com.grupo.sistema.chamados.dao.UserDAO;
import com.grupo.sistema.chamados.model.User;

import java.util.List;




public class UserService implements UserRepository{

    private UserDAO userDAO = new UserDAO();


    @Override
    // Validações para cadastrar o usuário
    public void CadastrarUsuario(User user) {
        //Caso
        if (user.getNome() == null || user.getNome().isBlank()){
            throw new IllegalArgumentException("O nome é um campo obrigatório");
        }

        if (user.getEmail() == null || user.getNome().isBlank()){
            throw new IllegalArgumentException("O email é um campo obrigatório");
        }

        if (user.getSetor() == null || user.getSetor().isBlank()){
            throw new IllegalArgumentException("O setor é um campo obrigatório");
        }

        userDAO.cadastrar(user);


    }

    @Override
    public List<User> searchUser(){
        return userDAO.SearchUser();
    }

    @Override
    //Validações para a edição do usuário
    public void editUser(User user){
        if (user.getIdUser()  <= 0){
            throw new IllegalArgumentException("ID do usuário obrigatório para edição");
        }

        userDAO.editUser(user);
    }

    @Override
    //Validações para a exclusão do usuário
    public void deleteUser(User user){
        if (user.getIdUser() <= 0){
            throw new IllegalArgumentException("ID do usuário obrigatório para exclusão");
        }

        userDAO.deleteUser(user);
    }
}
