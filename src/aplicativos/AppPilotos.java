package aplicativos;

import java.io.IOException;
import java.util.Scanner;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 2;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);
        
        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("Informe o nome do piloto:");
                String nome = in.next();
                System.out.println("Informe o cpf do piloto:");
                String cpf = in.next();

                try {
                    Pessoa pe = new Pessoa();
                    pe.setNome(nome);
                    pe.setCpf(cpf);
    
                    pilotos[qtdCadastrados] = pe;
                    qtdCadastrados++;
    
                    System.out.println("\nPiloto cadastrado com sucesso.");
                    in.nextLine();
                    voltarMenu(in);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Cadastro inválido!");
                    System.out.println(ex.getMessage());
                }

            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui
                for (int i = 0; i < qtdCadastrados; i++) {
                    Pessoa p = pilotos[i];
                    System.out.println("Nome: " + p.getNome());
                    System.out.println("CPF: " + p.getCpf());
                    System.out.println("-------");
                }

                voltarMenu(in);
            } else if (opcao == 3) {
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("Informe o cpf para busca:");
                String cpf = in.next();
                Boolean localizado = false;

                for (int i = 0; i < qtdCadastrados; i++) {
                    Pessoa p = pilotos[i];
                    if (p.getCpf().equals(cpf)) {
                       System.out.println("Nome: " + p.getNome());
                       System.out.println("CPF: " + p.getCpf());
                       localizado = true;
                    }
                }

                if (!localizado) {
                    System.out.println("CPF não localizado!");
                }
            } else if (opcao == 4) {
                System.out.println("Informe a quantidade de itens da nova lista:");
                int quantidade = in.nextInt();

                if (quantidade < MAX_ELEMENTOS) {
                    System.out.println("Quantidade menor que a quantidade atual de itens!");
                } else if (quantidade == MAX_ELEMENTOS) {
                    System.out.println("Quantidade igual a quantidade atual de itens!");
                } else {
                    Pessoa[] novaLista = new Pessoa[quantidade];
                    for (int i = 0; i < qtdCadastrados; i++) {
                        novaLista[i] = pilotos[i];
                    }

                    System.out.println("Lista atualizada!");
                    pilotos = novaLista;
                    MAX_ELEMENTOS = quantidade;
                }
                
            }
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}