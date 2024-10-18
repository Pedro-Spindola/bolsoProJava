/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Spindola
 * @date 17/10/2024
 * @brief Class Sistema
 */
public class Sistema {
    private Integer numeroDeContaNoSistema = 0;
    private List<IConta> contas = new ArrayList<>();

    public Sistema() {
        
    }    
    
    public void adicionarContas(Conta conta) throws Exception{
        if (conta != null) { contas.add(conta); numeroDeContaNoSistema++;
        }else throw new Exception("Usuário não pode ser nulo.");
    }

    public List<IConta> getContas() {
        return contas;
    }
    
    
}
