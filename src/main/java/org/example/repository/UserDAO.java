package org.example.repository;

import org.example.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    public void generateRandomUsers(int quantidade) {
        Stream.generate(() -> {
            String nome = "usuario" + new Random().nextInt(1000);
            String email = nome.toLowerCase() + "@exemplo.com";
            int idade = new Random().nextInt(100) + 1;
            return (new User(nome, email, idade));
        }).limit(quantidade)
                .forEach(this::adicionar);

    }

    public Boolean updateUserAndEmail(String nome, String novoEmail) {
        return usuarios.stream()
                .filter(u -> u.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .map(u -> {
                    u.setEmail(novoEmail);
                    return true;
                }).orElse(false);
    }
    public void deleteUser(String nomeDeUsuario) {
        Iterator<User> iterator = listar().iterator();
        while (iterator.hasNext()) {
            User usuario = iterator.next();
            if (usuario.getNome().equals(nomeDeUsuario)) {
                iterator.remove();
                return;
            }
        }
    }
            }



