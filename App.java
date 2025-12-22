import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== AFe (com ε) - Simulador ===");

        System.out.print("Informe os estados do autômato (ex: 0, 1, 2): ");
        String estadosLinha = sc.nextLine();
        List<Integer> idsEstados = parseListaInteiros(estadosLinha);

        Map<Integer, Estado> estadosMap = new HashMap<>();
        for (int id : idsEstados) {
            estadosMap.put(id, new Estado(id, false, false));
        }

        System.out.print("Informe o estado inicial: ");
        int idInicial = Integer.parseInt(sc.nextLine().trim());
        if (!estadosMap.containsKey(idInicial)) {
            System.out.println("ERRO: estado inicial não existe.");
            return;
        }
        estadosMap.get(idInicial).setEstadoInicial(true);

        System.out.println("Informe as transições (ex: 0a1, 0e2). Digite 'fim' para terminar:");
        List<Transicao> transicoes = new ArrayList<>();
        while (true) {
            String linha = sc.nextLine().trim();
            if (linha.equalsIgnoreCase("fim")) break;
            if (linha.isEmpty()) continue;

            Transicao t = parseTransicao(linha, estadosMap);
            if (t == null) {
                System.out.println("Transição inválida. Tente novamente (ex: 0a1, 0e2).");
                continue;
            }
            transicoes.add(t);
        }

        System.out.print("Informe os estados finais (ex: 1, 2): ");
        String finaisLinha = sc.nextLine();
        List<Integer> finais = parseListaInteiros(finaisLinha);
        for (int idFinal : finais) {
            Estado ef = estadosMap.get(idFinal);
            if (ef != null) ef.setEstadoFinal(true);
        }

        List<Estado> listaEstados = estadosMap.values()
                .stream()
                .sorted(Comparator.comparingInt(Estado::getEstado))
                .collect(Collectors.toList());

        Automato automato = new Automato(transicoes, listaEstados);
        automato.calculaFechoVazio();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1) Mostrar ε-fecho (fecho-vazio) de cada estado");
            System.out.println("2) Testar palavra (aceita/rejeita)");
            System.out.println("3) Mostrar transições");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");

            String op = sc.nextLine().trim();

            if (op.equals("0")) {
                System.out.println("Encerrando...");
                break;
            } else if (op.equals("1")) {
                mostrarFechos(automato);
            } else if (op.equals("2")) {
                System.out.print("Digite a palavra (vazia = ε, só aperte Enter): ");
                String palavra = sc.nextLine();
                boolean aceita = automato.aceitaPalavra(palavra);
                System.out.println(aceita ? "ACEITA" : "REJEITA");
            } else if (op.equals("3")) {
                mostrarTransicoes(automato);
            } else {
                System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }

    private static void mostrarFechos(Automato automato) {
        System.out.println("\n--- ε-fechos ---");
        for (Estado e : automato.getListaEstados()) {
            String fecho = e.getListaFecho().stream()
                    .map(Estado::toString)
                    .distinct()
                    .sorted(Comparator.comparingInt(Integer::parseInt))
                    .collect(Collectors.joining(", "));
            System.out.println("FechoE(" + e.getEstado() + ") = {" + fecho + "}");
        }
    }

    private static void mostrarTransicoes(Automato automato) {
        System.out.println("\n--- Transições ---");
        for (Transicao t : automato.getListaTransicao()) {
            char s = t.getSimbolo();
            String simb = (s == 'E') ? "ε" : String.valueOf(s);
            System.out.println(t.getEstadoOrigem().getEstado() + " --" + simb + "--> " + t.getEstadoDestino().getEstado());
        }
    }

    private static List<Integer> parseListaInteiros(String linha) {
        if (linha == null || linha.trim().isEmpty()) return new ArrayList<>();
        String[] partes = linha.split(",");
        List<Integer> out = new ArrayList<>();
        for (String p : partes) {
            String t = p.trim();
            if (!t.isEmpty()) out.add(Integer.parseInt(t));
        }
        return out;
    }

    private static Transicao parseTransicao(String s, Map<Integer, Estado> estadosMap) {
        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) i++;
        if (i == 0 || i >= s.length() - 1) return null;

        String origemStr = s.substring(0, i);
        char simbolo = s.charAt(i);
        String destinoStr = s.substring(i + 1);

        if (destinoStr.isEmpty()) return null;
        if (!destinoStr.chars().allMatch(Character::isDigit)) return null;

        int origemId = Integer.parseInt(origemStr);
        int destinoId = Integer.parseInt(destinoStr);

        Estado origem = estadosMap.get(origemId);
        Estado destino = estadosMap.get(destinoId);
        if (origem == null || destino == null) return null;

        if (simbolo == 'e' || simbolo == 'E') simbolo = 'E';

        return new Transicao(origem, destino, simbolo);
    }
}
