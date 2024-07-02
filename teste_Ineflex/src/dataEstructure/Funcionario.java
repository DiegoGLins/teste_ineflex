package dataEstructure;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{
    private BigDecimal salario;
    public String funcao;
    
    public Funcionario(String nome, LocalDate dataDeNascimento, BigDecimal salario, String funcao) {
super(nome, dataDeNascimento);
this.salario =  salario;
this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }
}
