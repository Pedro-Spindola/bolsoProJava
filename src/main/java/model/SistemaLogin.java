package model;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import model.Conta;
/**
 *
 * @author Pedro Spindola
 * @date 13/10/2024
 * @brief Class Sistema
 */
public class SistemaLogin {
    private Integer numeroDeUsuariosNoSistema = 0;
    private List<Usuario> usuarios = new ArrayList<>();
    
    public void adicionarUsuario(Usuario usuario) throws Exception{
        if (usuario != null){usuarios.add(usuario); numeroDeUsuariosNoSistema++;
        }else throw new Exception("Usuário não pode ser nulo.");
    }
    
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
    
}
