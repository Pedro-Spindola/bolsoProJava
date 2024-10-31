/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pedro Spindola
 * @date 21/10/2024
 * @brief Class Fatura
 */
public class Fatura {
    private Date faturaReferente = null; //mes e ano
    private float valor = 0;
    private String statusDaFatura = null;
    
    public Fatura(Date faturaReferente, float valor, String statusDaFatura) {
        this.faturaReferente = faturaReferente;
        this.valor = valor;
        this.statusDaFatura = statusDaFatura;
    }
    
    public String mostrarFatura(){
        SimpleDateFormat formatadorCompleto = new SimpleDateFormat("MMMM/yyyy"); // Janeiro/2024
        return "Fatura:\n" + 
               "- Referente: " + formatadorCompleto.format(faturaReferente) +
               " Valor: " + valor + 
               " Stutas: " + statusDaFatura;
    }
    
    public void descontarSaldo(float valorDescontar) {
        valor -= valorDescontar;
    }

    public void restituirSaldo(float valorRestituir){
        valor += valorRestituir;
    }
    
    public void correcaoSaldoLacamento(float valorAntigo, float newValor){
        valor += valorAntigo;
        valor -= newValor;
    }
    
    public Date getFaturaReferente() {
        return faturaReferente;
    }

    public void setFaturaReferente(Date faturaReferente) {
        this.faturaReferente = faturaReferente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getStatusDaFatura() {
        return statusDaFatura;
    }

    public void setStatusDaFatura(String statusDaFatura) {
        this.statusDaFatura = statusDaFatura;
    }
}
