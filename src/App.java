import entidades.Familia;
import persistencia.ClienteDAO;
import persistencia.FamiliaDAO;

public class App {
    public static void main(String[] args) throws Exception {
       
       /*CasaDAO casaDAO = new CasaDAO();
        List<Casa> casas = casaDAO.listarTodasLasCasas();
        System.out.println(casas);*/

        ClienteDAO clienteDao = new ClienteDAO();
        /*List<Cliente> clientes = clienteDao.listarTodosLosClientes();
        System.out.println(clientes);*/

        FamiliaDAO familiaDAO = new FamiliaDAO();
        Familia familia = new Familia();
        familia = familiaDAO.buscarFamiliaPorId(3);
        System.out.println(familia.toString());
        
       
        
    }
}
