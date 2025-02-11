package persistencia;

import entidades.Estancia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstanciaDAO extends DAO {
    public void guardarEstancia(Estancia estancia) throws Exception {
        if (estancia == null) {
            throw new Exception("La estancia no puede ser nula.");
        }
        String sql = "INSERT INTO estancias (id_estancia, id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta) VALUES ("
                + estancia.getIdEstancia() + ", "
                + estancia.getIdCliente() + ", "
                + estancia.getIdCasa() + ", '"
                + estancia.getNombreHuesped() + "', '"
                + estancia.getFechaDesde() + "', '"
                + estancia.getFechaHasta() + "');";
        insertarModificarEliminarDataBase(sql);
    }

    public List<Estancia> listarTodasLasEstancias() throws Exception {
        String sql = "SELECT * FROM estancias;";
        consultarDataBase(sql);

        List<Estancia> estancias = new ArrayList<>();
        while (resultSet.next()) {
            Estancia estancia = new Estancia();
            estancia.setIdEstancia(resultSet.getInt("id_estancia"));
            estancia.setIdCliente(resultSet.getInt("id_cliente"));
            estancia.setIdCasa(resultSet.getInt("id_casa"));
            estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
            estancia.setFechaDesde(resultSet.getString("fecha_desde"));
            estancia.setFechaHasta(resultSet.getString("fecha_hasta"));
            estancias.add(estancia);
        }
        return estancias;
    }

    public void eliminarEstanciaPorId(int id) throws Exception {
        String sql = "DELETE FROM estancias WHERE id_estancia = " + id;
        insertarModificarEliminarDataBase(sql);
    }

     public Estancia buscarEstanciaPorId(int id) throws Exception {
        String sql = "SELECT * from estancias where id_estancia = " + id + ";";
        consultarDataBase(sql);
        Estancia estanciaEncontrada = null;
        while (resultSet.next()) {
            estanciaEncontrada = crearEstancia();
        }

        if (estanciaEncontrada == null) {
            System.out.printf("Estancia con ID %s no encontrada", id);
        } else {
            System.out.println(estanciaEncontrada.toString());
        }

        return estanciaEncontrada;
    }

    private Estancia crearEstancia() throws SQLException {
        return new Estancia(
                resultSet.getInt("id_estancia"),
                resultSet.getInt("id_cliente"),
                resultSet.getInt("id_casa"),
                resultSet.getString("nombre_huesped"),
                resultSet.getString("fecha_desde"),
                resultSet.getString("fecha_hasta"));
    }
}