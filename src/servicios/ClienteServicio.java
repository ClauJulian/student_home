package servicios;

import entidades.Cliente;
import entidades.Comentario;
import entidades.Estancia;
import java.util.List;
import persistencia.CasaDAO;
import persistencia.ClienteDAO;
import persistencia.ComentarioDAO;
import persistencia.EstanciaDAO;

public class ClienteServicio {
    private ClienteDAO clienteDAO;
    private EstanciaDAO estanciaDAO;
    private ComentarioDAO comentarioDAO;
    private CasaDAO casaDAO;

    public ClienteServicio() {
        clienteDAO = new ClienteDAO();
        estanciaDAO = new EstanciaDAO();
        comentarioDAO = new ComentarioDAO();
        casaDAO = new CasaDAO();
        
    }

    public void listarClientesConEstancias() throws Exception {
        List<Estancia> estanciasTodas = estanciaDAO.listarTodasLasEstancias();
        List<Comentario> comentariosTodos = comentarioDAO.listarTodosLosComentarios();

        System.out.println("****** Clientes con Estancias ******");
        for (Estancia estancia: estanciasTodas){
            
            Cliente cliente = clienteDAO.buscarClientePorID(estancia.getIdCliente());
            System.out.println(cliente);
            for (Comentario comentario : comentariosTodos) {
                if (comentario.getIdCasa() == estancia.getIdCasa()) {
                    System.out.println("Comentario de la casa: " + comentario.getComentario());
                    System.out.println("-----------------");
                }
                
            }
        }

    }
}

