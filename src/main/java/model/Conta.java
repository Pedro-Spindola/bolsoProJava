
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * @author Pedro Spindola
 * @date 13/10/2024
 * @brief Class Conta
 */
public class Conta{
    private Integer id = 0;
    private String nomeDoBanco = "";
    private float saldo = 0;
    private float investimentos = 0;
    private boolean cartaoCreditos = false;
    private float limiteDoCartao = 0;
    private float fatura = 0;
    private Integer fechamentoDaFatura = null;
    private Integer vencimentoDaFatura = null;
    private List<Fatura> faturas = new ArrayList<>();
    
    public Conta(Integer id, String nomeDoBanco, float saldo, float investimentos) throws Exception{
        this.id = id;
        this.nomeDoBanco = nomeDoBanco;
        this.saldo = saldo;
        this.investimentos = investimentos;
    }
    
    public Conta(Integer id, String nomeDoBanco, float saldo, float investimentos, boolean cartaoCreditos, float limiteDoCartao, Integer fechamentoDaFatura, Integer vencimentoDaFatura) throws Exception{
        this.id = id;
        this.nomeDoBanco = nomeDoBanco;
        this.saldo = saldo;
        this.investimentos = investimentos;
        this.cartaoCreditos = cartaoCreditos;
        this.limiteDoCartao = limiteDoCartao;
        this.fechamentoDaFatura = fechamentoDaFatura;
        this.vencimentoDaFatura = vencimentoDaFatura;
        verificarFaturaProximoMes();
    }

    public void adicionarCartaoDeCredito(float limiteDoCartao, Integer vencimentoDoCartao, Integer vencimentoDaFatura) throws Exception {
        cartaoCreditos = true;
        this.limiteDoCartao = limiteDoCartao;
        this.fechamentoDaFatura = vencimentoDoCartao;
        this.vencimentoDaFatura = vencimentoDaFatura;
        verificarFaturaProximoMes();
    }
    
    public float getLimiteDisponivel(){
        float limiteDisponivel = limiteDoCartao;
        for (Fatura fatura : faturas){
            limiteDisponivel -= fatura.getValor();
        }
        return limiteDisponivel;
    }
    
    public void descontarSaldo(float valor) {
        saldo += valor;
    }

    public void restituirSaldo(float valor){
        saldo += valor;
    }
    
    public void correcaoSaldoLacamento(float valor, float newValor){
        saldo -= valor;
        saldo += newValor;
    }

    public void verificarFaturaProximoMes() {
        if(cartaoCreditos){
            Calendar calendar = Calendar.getInstance();
            int diaAtual = calendar.get(Calendar.DAY_OF_MONTH);
            int mesAtual = calendar.get(Calendar.MONTH);
            int anoAtual = calendar.get(Calendar.YEAR);

            if (diaAtual > fechamentoDaFatura) {
                calendar.add(Calendar.MONTH, 1);
                int mesProximo = calendar.get(Calendar.MONTH);
                int anoProximo = calendar.get(Calendar.YEAR);
                if (!isFaturaParaProximoMes(mesProximo, anoProximo)) {
                    Fatura novaFatura = new Fatura(calendar.getTime(), 0, "ABERTO");
                    faturas.add(novaFatura);
                }
            } else {
                if (!isFaturaParaProximoMes(mesAtual, anoAtual)) {
                    Fatura novaFatura = new Fatura(calendar.getTime(), 0, "ABERTO");
                    faturas.add(novaFatura);
                }
            }
        }
    }
    
    public void adicionarFaturasFuturas(int numParcelas) {
        if (cartaoCreditos) {
            Calendar calendar = Calendar.getInstance();
            
            for (int i = 1; i <= numParcelas; i++) {
                calendar.add(Calendar.MONTH, 1);
                int mesProximo = calendar.get(Calendar.MONTH);
                int anoProximo = calendar.get(Calendar.YEAR);

                if (!isFaturaParaProximoMes(mesProximo, anoProximo)) {
                    Fatura novaFatura = new Fatura(calendar.getTime(), 0, "ABERTO");
                    faturas.add(novaFatura);
                }
            }
        }
    }

    private boolean isFaturaParaProximoMes(int mesProximo, int anoProximo) {
        for (Fatura fatura : faturas) {
            Calendar faturaCal = Calendar.getInstance();
            faturaCal.setTime(fatura.getFaturaReferente());
            if (faturaCal.get(Calendar.MONTH) == mesProximo && faturaCal.get(Calendar.YEAR) == anoProximo) {
                return true;
            }
        }
        return false;
    }
    
    public String mostrarConta(){
        return "Conta:\n" + 
               "id: " + id +
               " nomeDoBanco: " + nomeDoBanco + 
               " saldo: " + saldo +
               " investimentos: " + investimentos + 
               " cartaoCreditos: " + cartaoCreditos +
               " limiteDoCartao: " + limiteDoCartao +
               " fatura: " + mostratListaDeFaturas() +
               " fechamentoDaFatura: " + fechamentoDaFatura +
               " vencimentoDaFatura: " + vencimentoDaFatura +
               " faturas: " + faturas;
    }

    public String mostratListaDeFaturas() {
        StringBuilder text = new StringBuilder(); // Usar StringBuilder para melhor performance
        for (Fatura fatura : faturas) {
            text.append(fatura.mostrarFatura());
        }
        return text.toString(); // Retorna a string completa
    }
    
    public List<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDoBanco() {
        return nomeDoBanco;
    }

    public void setNomeDoBanco(String nomeDoBanco) {
        this.nomeDoBanco = nomeDoBanco;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(float investimentos) {
        this.investimentos = investimentos;
    }

    public boolean isCartaoCreditos() {
        return cartaoCreditos;
    }

    public void setCartaoCreditos(boolean cartaoCreditos) {
        this.cartaoCreditos = cartaoCreditos;
    }

    public float getLimiteDoCartao() {
        return limiteDoCartao;
    }

    public void setLimiteDoCartao(float limiteDoCartao) {
        this.limiteDoCartao = limiteDoCartao;
    }

    public float getFatura() {
        return fatura;
    }

    public void setFatura(float fatura) {
        this.fatura = fatura;
    }

    public Integer getFechamentoDaFatura() {
        return fechamentoDaFatura;
    }

    public void setFechamentoDaFatura(Integer fechamentoDaFatura) {
        this.fechamentoDaFatura = fechamentoDaFatura;
    }

    public Integer getVencimentoDaFatura() {
        return vencimentoDaFatura;
    }

    public void setVencimentoDaFatura(Integer vencimentoDaFatura) {
        this.vencimentoDaFatura = vencimentoDaFatura;
    }
    
    
}
