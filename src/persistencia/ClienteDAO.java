package persistencia;

import entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAO {
    
    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }
        String sql = "INSERT INTO clientes (nombre, calle, numero, codigo_postal, ciudad, pais, email) VALUES ('"
                + cliente.getNombre() + "', '"
                + cliente.getCalle() + "', "
                + cliente.getNumero() + ", "
                + cliente.getCodigoPostal() + ", '"
                + cliente.getCiudad() + "', '"
                + cliente.getPais() + "', '"
                + cliente.getEmail() + "')";
        insertarModificarEliminarDataBase(sql);
    }

    public List<Cliente> listarTodosLosClientes() throws Exception {
        String sql = "SELECT id_cliente, nombre, email FROM clientes;";
        consultarDataBase(sql);
        
        List<Cliente> clientes = new ArrayList<>();
        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("id_cliente"));
            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setEmail(resultSet.getString("email"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public void eliminarClientePorId(int id) throws Exception {
        String sql = "DELETE FROM clientes WHERE id_cliente = " + id;
        insertarModificarEliminarDataBase(sql);
    }


    public Cliente buscarClientePorID(int id) throws Exception {
        // script sql
        String sql = "SELECT id_cliente, nombre, calle, numero, codigo_postal, ciudad, pais, email FROM clientes WHERE id_cliente = " + id + ";";
        // método DAO
        consultarDataBase(sql);
        Cliente cliente = null;

        while (resultSet.next()) {
            cliente = crearCliente();
        }
        if (cliente == null) {
            System.out.printf("Casa con ID %s no encontrada", id);
        } else {
            //System.out.println(cliente.toString());
        }
        return cliente;
    }

    public Cliente crearCliente() throws Exception {
        // validar resulSet
        if (resultSet == null) {
            throw new Exception("No existe el registro!!");
        }
        // crear un nuevo cliente
        Cliente cliente = new Cliente(
        resultSet.getInt("id_cliente"),
        resultSet.getString("nombre"),
        resultSet.getString("calle"),
        resultSet.getInt("numero"),
        resultSet.getInt("codigo_postal"),
        resultSet.getString("ciudad"),
        resultSet.getString("pais"),
        resultSet.getString("email"));
        return cliente;
    }
}

