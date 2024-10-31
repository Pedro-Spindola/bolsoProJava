package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pedro Spindola
 * @date 17/10/2024
 * @brief Class Sistema
 */
public class Sistema {
    private Integer numeroDeContaNoSistema = 0;
    private Integer numeroDeLancamentos = 0;
    private List<Conta> contas = new ArrayList<>();
    private List<Lancamentos> todosLancamentos = new ArrayList<>();
    private Usuario usuario = null;
    public Sistema() {
        
    }    
    
    // MÉTODO PEGAR O VALOR TOTAL EM CONTAS
    public float valorTotalEmConta(){
        float total = 0;
        for (Conta conta : contas){
            total += conta.getSaldo();
        }
        return total;
    }
    
    // MÉTODO PEGAR O VALOR TOTAL EM INVESTIMENTOS
    public float valorTotalEmInvestimentos(){
        float total = 0;
        for (Conta conta : contas){
            total += conta.getInvestimentos();
        }
        return total;
    }
    
    // MÉTODO PEGAR O VALOR TOTAL EM RECEITAS
    public float valorMensalEmReceitas(){
        float total = 0;
        Calendar calendar = Calendar.getInstance();
        int mesAtual = calendar.get(Calendar.MONTH);
        int anoAtual = calendar.get(Calendar.YEAR);
        
        for (Lancamentos lancamento : todosLancamentos){
            Date dataLancamento = lancamento.getData();
            Calendar lancamentoCalendar = Calendar.getInstance();
            lancamentoCalendar.setTime(dataLancamento);
            int mesLancamento = lancamentoCalendar.get(Calendar.MONTH);
            int anoLancamento = lancamentoCalendar.get(Calendar.YEAR); 
            if(mesLancamento == mesAtual && anoLancamento == anoAtual && lancamento.getValor() > 0){
                if(lancamento.getParcelas() == 1){
                    total += lancamento.getValor();
                }
            }
        }
        return total;
    }
    
    // MÉTODO PEGAR O VALOR TOTAL EM DESPESAS
    public float valorMensalEmDespesas(){
        float total = 0;
        Calendar calendar = Calendar.getInstance();
        int mesAtual = calendar.get(Calendar.MONTH);
        int anoAtual = calendar.get(Calendar.YEAR);
        
        for (Lancamentos lancamento : todosLancamentos){
            Date dataLancamento = lancamento.getData();
            Calendar lancamentoCalendar = Calendar.getInstance();
            lancamentoCalendar.setTime(dataLancamento);
            int mesLancamento = lancamentoCalendar.get(Calendar.MONTH);
            int anoLancamento = lancamentoCalendar.get(Calendar.YEAR); 
            if(mesLancamento == mesAtual && anoLancamento == anoAtual && lancamento.getValor() < 0){
                if(lancamento.getParcelas() == 1){
                    total += lancamento.getValor();
                } else{
                    for (Date dateParcela : lancamento.getDatasParcelas()){
                        Date dataLancamentoParcela = dateParcela;
                        Calendar lancamentoParcelaCalendar = Calendar.getInstance();
                        lancamentoParcelaCalendar.setTime(dataLancamentoParcela);
                        int mesParcelaLancamento = lancamentoParcelaCalendar.get(Calendar.MONTH);
                        int anoParcelaLancamento = lancamentoParcelaCalendar.get(Calendar.YEAR);
                        if(mesParcelaLancamento == mesAtual && anoParcelaLancamento == anoAtual){
                            total += (lancamento.getValor() / lancamento.getParcelas());
                        }
                    }
                }
            }
        }
        return total;
    }
    
    // ADICIONAR UMA NOCA CONTA DE BANCO NO SISTEMA
    public void adicionarConta(Conta conta) throws Exception{
        if (conta != null) { contas.add(conta); numeroDeContaNoSistema++;
        }else throw new Exception("Conta não pode ser adicionar nula...");
    }
    // ADICIONAR OS LANCAMENTOS
    public void adicionarLancamento(Lancamentos lancamento) throws Exception{
        if (lancamento != null){
            Conta conta = encontrarConta(lancamento.getConta());
            if (conta != null){
                if (lancamento.isCredito()){
                    Date dataLancamento = lancamento.getData();
                    Calendar calendarLancamento = Calendar.getInstance();
                    calendarLancamento.setTime(dataLancamento);
                    int diaDoLancamento = calendarLancamento.get(Calendar.DAY_OF_MONTH);
                    boolean passouFechamento = diaDoLancamento > conta.getFechamentoDaFatura();
                    if(lancamento.getParcelas() > 1){
                        // LANÇAMENTO PARCELADO
                        int parcelas = lancamento.getParcelas();
                        float valorPorParcela = lancamento.getValor() / parcelas;
                        verificarFaturasExistenteParaTodasAsParcelas(lancamento);
                        
                        for (int i = 0; i < parcelas; i++){
                            Calendar dataParcela = (Calendar) calendarLancamento.clone();
                            if(passouFechamento)dataParcela.add(Calendar.MONTH, i + 1);
                            else dataParcela.add(Calendar.MONTH, i);
                            
                            Fatura faturaAtual = null;
                            
                            if(i == 0)faturaAtual = encontrarFatura(conta, passouFechamento);
                            else faturaAtual = encontrarFaturaPorData(conta, dataParcela);
                            
                            if (faturaAtual != null) {
                                faturaAtual.descontarSaldo(valorPorParcela);
                                lancamento.adicionarFaturaReferente(faturaAtual);
                                Date dateParcelatypeDate = dataParcela.getTime();
                                lancamento.getDatasParcelas().add(dateParcelatypeDate);
                            } else throw new Exception("Fatura do mês atual não encontrada para a conta.");
                        }
                        todosLancamentos.add(lancamento);
                    } else{
                        // LANÇAMENTO PARCELA UNICA
                        Fatura faturaAtual = encontrarFatura(conta, passouFechamento);
                        if (faturaAtual != null) {
                            faturaAtual.descontarSaldo(lancamento.getValor());
                            lancamento.adicionarFaturaReferente(faturaAtual);
                            todosLancamentos.add(lancamento);
                        } else throw new Exception("Fatura do mês atual não encontrada para a conta.");
                    }
                } else{
                    // LANÇAMENTO NO DEBITO
                    ((Conta) conta).descontarSaldo(lancamento.getValor());
                    todosLancamentos.add(lancamento);
                }
            } else throw new Exception("Conta não encontrada na lista para o lançamento.");
        } else throw new Exception("Lancamento não pode ser null.");
    }
    // REMOVER OS LANCAMENTOS
    public void removerLancamentos(Lancamentos lancamento) throws Exception{
        if (todosLancamentos.contains(lancamento)) {
            Conta conta = encontrarConta(lancamento.getConta());
            if (conta != null) {
                if(lancamento.isCredito()){
                    // apagar a fatura
                    List<Fatura> faturasDoLancamento = lancamento.getFaturas();
                    List<Fatura> faturasDaConta = conta.getFaturas();

                    for (Fatura faturaLancamentoUnica : faturasDoLancamento) {
                        for (Fatura faturaContaUnica : faturasDaConta) {
                            if (faturaLancamentoUnica.equals(faturaContaUnica)) {
                                faturaContaUnica.restituirSaldo((lancamento.getValor() / lancamento.getParcelas()));
                            }
                        }
                    }
                } else{
                    ((Conta) conta).restituirSaldo(lancamento.getValor());
                }
                todosLancamentos.remove(lancamento);
            } else throw new Exception("Conta não encontrada para o lançamento.");
        } else throw new Exception("Lançamento não encontrado na lista.");
    }
    // EDITAR OS LANCAMENTOS
    public void editarLancamentos(Lancamentos lancamentoAtualizado, Lancamentos lancamentoOriginal) throws Exception{
        if (lancamentoAtualizado == null) throw new Exception("Lançamento atualizado não pode ser nulo.");
        Conta contaOriginal = encontrarConta(lancamentoOriginal.getConta());
        if (contaOriginal != null){
            if(lancamentoAtualizado.isCredito()){
                List<Fatura> faturasDoLancamento = lancamentoOriginal.getFaturas();
                List<Fatura> faturasDaConta = contaOriginal.getFaturas();

                for (Fatura faturaLancamentoUnica : faturasDoLancamento) {
                    for (Fatura faturaContaUnica : faturasDaConta) {
                        if (faturaLancamentoUnica.equals(faturaContaUnica)) {
                            faturaContaUnica.correcaoSaldoLacamento((lancamentoOriginal.getValor() / lancamentoOriginal.getParcelas()), (lancamentoAtualizado.getValor() / lancamentoAtualizado.getParcelas()));
                        }
                    }
                }
            } else{
                ((Conta) contaOriginal).correcaoSaldoLacamento(lancamentoOriginal.getValor(), lancamentoAtualizado.getValor());
            }
        } else throw new Exception("Conta não encontrada para o lançamento.");        
        lancamentoOriginal.setDescricao(lancamentoAtualizado.getCategorias());
        lancamentoOriginal.setValor(lancamentoAtualizado.getValor());
        lancamentoOriginal.setConta(lancamentoAtualizado.getConta());
        lancamentoOriginal.setCategorias(lancamentoAtualizado.getCategorias());
        lancamentoOriginal.setSubcategorias(lancamentoAtualizado.getSubcategorias());
    }
    // MÉTODO VERIFIR SE EXISTE FATURA DISPONIVEL PARA AS PARCELAR A SER ACRESCENTADA
    public void verificarFaturasExistenteParaTodasAsParcelas(Lancamentos lancamento) throws Exception{
        if (lancamento != null){
            Conta conta = encontrarConta(lancamento.getConta());
            if (conta != null){
                Integer qtnParcelas = lancamento.getParcelas();
                conta.adicionarFaturasFuturas(qtnParcelas);
            }else throw new Exception("Conta não encontrada para o lançamento.");
        } else throw new Exception("Lançamento não encontrado na lista.");
        //adicionarFaturasFuturas(qtnParcelas);
    }  
    // MÉTODO PARA TRANSFERÊNCIA DE DINHEIRO ENTRE CONTAS.
    public void transferenciasEntreContas(Conta contaMandante, Conta contaReceptora, float valor)  throws Exception{
        if (contaMandante == null || contaMandante == null) throw new Exception("Contas não pode ser nulo.");
        if (contaMandante instanceof Conta) ((Conta) contaMandante).descontarSaldo(valor);
        if (contaReceptora instanceof Conta) ((Conta) contaReceptora).restituirSaldo(valor);
    }  
    // MÉTODO PARA ENCONTRAR A CONTA DENTRO DA MINHA LISTA DE CONTAS DISPONIVÉL.
    private Conta encontrarConta(String nomeConta) {
        for (Conta conta : contas) {
            if (conta.getNomeDoBanco().equals(nomeConta)) return conta;
        }
        return null;
    }  
    // MÉTODO PARA ENCONTRAR FATURA DENTRO DA MINHA LISTA DE FATURAS DISPONIVÉL.
    private Fatura encontrarFatura(Conta conta, boolean passouFechamento) {
        Calendar calendar = Calendar.getInstance();
        int mesAtual = calendar.get(Calendar.MONTH);
        int anoAtual = calendar.get(Calendar.YEAR);

        for (Fatura fatura : conta.getFaturas()) {
            Calendar faturaCalendar = Calendar.getInstance();
            faturaCalendar.setTime(fatura.getFaturaReferente());

            int mesFatura = faturaCalendar.get(Calendar.MONTH);
            int anoFatura = faturaCalendar.get(Calendar.YEAR);

            if (mesFatura == (mesAtual + (passouFechamento ? 1 : 0)) % 12 &&
                anoFatura == anoAtual + (mesAtual + (passouFechamento ? 1 : 0)) / 12) {
                return fatura;
            }
        }
        return null;
    } 
    // MÉTODO PARA ENCONTRAR FATURA DENTRO DA MINHA LISTA DE FATURAS DISPONIVÉL PASSANDO UMA DATA.
    private Fatura encontrarFaturaPorData(Conta conta, Calendar dataParcela) {
        for (Fatura fatura : conta.getFaturas()) {
            Calendar faturaCalendar = Calendar.getInstance();
            faturaCalendar.setTime(fatura.getFaturaReferente());

            if (faturaCalendar.get(Calendar.MONTH) == dataParcela.get(Calendar.MONTH) &&
                faturaCalendar.get(Calendar.YEAR) == dataParcela.get(Calendar.YEAR)) {
                return fatura;
            }
        }
        return null;
    }  
    //AUMENTAR O CONTADOR DE NUMEROS DE CONTAS NO SISTEMA.
    public Integer idParaUmaNovaConta(){
        numeroDeContaNoSistema++;
        return numeroDeContaNoSistema;
    }
    
    public List<Conta> getContas() {
        return contas;
    }

    public List<Lancamentos> getTodosLancamentos() {
        return todosLancamentos;
    }
    
    public void mostrarContas() {
        System.out.println("\nListagem de Contas:");
        for (Conta conta : contas) {
            System.out.println(conta.mostrarConta());
        }
    }
    
    public void mostrarLancamentos() {
        System.out.println("\nListagem de Lançamentos:");
        for (Lancamentos lancamento : todosLancamentos) {
            System.out.println(lancamento.mostrarLancamentos());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
