
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pedro Spindola
 * @date 21/10/2024
 * @brief Class Lancamentos
 */
public class Lancamentos {
    private String descricao = "";
    private float valor = 0;
    private Date data = null;
    private String conta = "";
    private boolean credito = false;
    private String categorias = "";
    private String subcategorias = "";
    private Integer parcelas = 0;
    private List<Fatura> faturas = new ArrayList<>();
    private List<Date> datasParcelas = new ArrayList<>();
    
    public Lancamentos(String descricao, float valor, String conta, boolean credito, String categorias, String subcategorias, Integer parcelas, Date newData) {
        this.descricao = descricao;
        this.valor = valor;
        Date dataAtual = new Date();
        if(newData != null){
            data = newData;
        } else data = dataAtual;
        this.conta = conta;
        this.credito = credito;
        this.categorias = categorias;
        this.subcategorias = subcategorias;
        this.parcelas = parcelas;
    }
    
    public void adicionarFaturaReferente(Fatura fatura) {
        if (fatura != null){
            faturas.add(fatura);
        } else{
            System.out.println("Fatura é nula");
        }
    }
    
    public Fatura encontrarFaturaPorReferencia(Fatura faturaReferencia) {
    for (Fatura fatura : faturas) {
        if (fatura.equals(faturaReferencia)) {
            return fatura;
        }
    }
    
    return null;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(String subCategorias) {
        this.subcategorias = subCategorias;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }

    public List<Date> getDatasParcelas() {
        return datasParcelas;
    }

    public void setDatasParcelas(List<Date> datasParcelas) {
        this.datasParcelas = datasParcelas;
    }

    
    
    
    public String mostrarLancamentos(){
        return "Lançamento: " + 
               " Descricao: " + descricao + 
               " Valor: " + valor +
               " Data: " + data + 
               " Conta: " + conta +
               " Categorias: " + categorias +
               " SubCategorias: " + subcategorias +
               " Datas Parcelas : " + datasParcelas;
    }
}
