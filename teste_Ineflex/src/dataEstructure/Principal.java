package dataEstructure;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000,10,18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990,5,12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961,5,2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988,10,14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995,1,5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993,3,31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994,7,8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,5,24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996,9,2), new BigDecimal("2799.93"), "Gerente"));


        funcionarios.removeIf(f -> f.getNome().equals("João"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario f: funcionarios) {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s\n",
                    f.getNome(), f.getDataDeNascimento().format(formatter),
                    f.getSalario().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".",","),
                    f.getFuncao());
        }

//atualiza os salarios com 10% de aumento;
        for (Funcionario f: funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(BigDecimal.valueOf(1.10));
        f.setSalario(novoSalario);
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Imprimir os funcionários, agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s\n",
                        f.getNome(), f.getDataDeNascimento().format(formatter),
                        f.getSalario().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","),
                        f.getFuncao());
            }
        }

        //imprimir funcionarios com a maior idade. Não realizado
        //imprimir funcionarios em ordem alfabética. Não realizado

        System.out.println("Funcionários que fazem aniversário em Outubro e Dezembro:");
        for (Funcionario f : funcionarios) {
            int month = f.getDataDeNascimento().getMonthValue();
            if (month == 10 || month == 12) {
                System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s\n",
                        f.getNome(), f.getDataDeNascimento().format(formatter),
                        f.getSalario().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","),
                        f.getFuncao());
            }

            //Imprimir o total dos salários dos funcionários
            BigDecimal totalSalarios = funcionarios.stream()
                    .map(Funcionario::getSalario)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.printf("Total dos salários: %s\n", totalSalarios.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString().replace(".", ","));

            //Imprimir quantos salários mínimos ganha cada funcionário
            BigDecimal salarioMinimo = new BigDecimal("1212.00");
            System.out.println("Quantidade de salários mínimos por funcionário:");
            for (Funcionario func : funcionarios) {
                BigDecimal salariosMinimos = func.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_EVEN);
                System.out.printf("Nome: %s, Salários mínimos: %s\n",
                        f.getNome(), salariosMinimos.toString().replace(".", ","));
            }
        }
    }
}
