package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDAO {
    private List<User> usuarios = new ArrayList<>();

    public boolean hasEmail(String email) {
        return usuarios.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public void adicionar(User user) {
        usuarios.add(user);
    }

    public List<User> listar() {
        return usuarios;
    }

    public List<User> buscarPorNome(String nome) {
        return usuarios.stream()
                .filter(u -> u.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }
}
