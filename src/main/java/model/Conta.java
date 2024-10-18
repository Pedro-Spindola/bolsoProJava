
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Spindola
 * @date 13/10/2024
 * @brief Class Conta
 */
public class Conta implements IConta{
    private Integer id = 0;
    private String intituicao = "";
    private float saldo = 0;
    private float investimento = 0;

    public Conta(Integer id, String intituicao, float saldo, float investimento) {
        this.id = id;
        this.intituicao = intituicao;
        this.saldo = saldo;
        this.investimento = investimento;
    }

    public String getIntituicao() {
        return intituicao;
    }

    public void setIntituicao(String intituicao) {
        this.intituicao = intituicao;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getInvestimento() {
        return investimento;
    }

    public void setInvestimento(float investimento) {
        this.investimento = investimento;
    }

    public Integer getId() {
        return id;
    }
    
    
}
