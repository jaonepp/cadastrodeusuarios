package org.example;

import org.example.repository.UserDAO;
import java.util.ArrayList;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        ArrayList<User> usuarios = new ArrayList<>();

        while (true) {
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Listar usuarios");
            System.out.println("3 - Buscar por nome");
            System.out.println("4 - sair");
            System.out.println("5 - Gerar user aleatorio");
            System.out.println("6 - Atualizar usuario");
            System.out.println("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.println("Nome: ");
                String nome = scanner.nextLine();

                while (nome.strip().isEmpty() || !nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                    System.out.println("Nome inválido! Use apenas letras:");
                    nome = scanner.nextLine();
                }

                System.out.println("Email: ");
                String email = scanner.nextLine();
                while (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    System.out.println("Email inválido. Tente novamente:");
                    email = scanner.nextLine();
                }

                boolean hasUser = userDAO.hasEmail(email);
                if (hasUser) {
                    System.out.println("Já existe um usuário com esse email!");
                    continue;
                }
                System.out.println("idade: ");
                int idade = scanner.nextInt();
                while (idade <= 0 || idade > 100) {
                    System.out.println("Idade invalida!");
                    idade = scanner.nextInt();
                    scanner.nextLine();
                }

                userDAO.adicionar(new User(nome, email, idade));
                System.out.println("Usuário cadastrado com sucesso!");

            } else if (opcao == 2) {
                System.out.println("Usuarios cadastrados: ");
                userDAO.listar().forEach(System.out::println);

            } else if (opcao == 3) {
                System.out.println("Digite o nome para busca: ");
                String busca = scanner.nextLine();
                var encontrados = userDAO.buscarPorNome(busca);
                if (encontrados.isEmpty()) {
                    System.out.println("Usuario nao encontrado");
                } else {
                    encontrados.forEach(u -> System.out.println("Encontrado" + u));
                }
            } else if (opcao == 4) {
                System.out.println("Encerrando...");
                break;
            } else if (opcao == 5){
                userDAO.generateRandomUsers(2);
                System.out.println("Usuerio gerado com sucesso");
            } else if (opcao == 6) {
                System.out.println("Digite o nome de usuario que deseja atualizar: ");
                String novoNome = scanner.nextLine();

                System.out.println("Digite o novo email: ");
                String novoEmail = scanner.nextLine();

                boolean atualizado = userDAO.updateUserAndEmail(novoNome,novoEmail);

                if (!atualizado) {
                    System.out.println("O nome de usuario não foi atualizado");
                }
            } else {
                System.out.println("opcao invalida");
            }
        }
        scanner.close();
    }
}
