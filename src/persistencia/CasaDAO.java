package persistencia;

import entidades.Casa;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CasaDAO extends DAO {
    
    public void guardarCasa(Casa casa) throws Exception {
        if (casa == null) {
            throw new Exception("La casa no puede ser nula");
        }
        String sql = "INSERT INTO casas (calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta, tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda) VALUES ('"
                + casa.getCalle() + "', "
                + casa.getNumero() + ", '"
                + casa.getCodigoPostal() + "', '"
                + casa.getCiudad() + "', '"
                + casa.getPais() + "', '"
                + casa.getFechaDesde() + "', '"
                + casa.getFechaHasta() + "', '"
                + casa.getTiempoMinimo() + "', '"
                + casa.getTiempoMaximo() + "', "
                + casa.getPrecioHabitacion() + ", '"
                + casa.getTipoVivienda() + "')";
        insertarModificarEliminarDataBase(sql);
    }

    public List<Casa> listarTodasLasCasas() throws Exception {
        String sql = "SELECT id_casa, calle, numero, ciudad, pais, fecha_desde, fecha_hasta, precio_habitacion, tipo_vivienda FROM casas";
        consultarDataBase(sql);

        List<Casa> casas = new ArrayList<>();
        while (resultSet.next()) {
            Casa casa = new Casa();
            casa.setIdCasa(resultSet.getInt("id_casa"));
            casa.setCalle(resultSet.getString("calle"));
            casa.setNumero(resultSet.getInt("numero"));
            casa.setCiudad(resultSet.getString("ciudad"));
            casa.setPais(resultSet.getString("pais"));
            casa.setFechaDesde(resultSet.getDate("fecha_desde").toLocalDate());
            casa.setFechaHasta(resultSet.getDate("fecha_hasta").toLocalDate());
            casa.setPrecioHabitacion(resultSet.getDouble("precio_habitacion"));
            casa.setTipoVivienda(resultSet.getString("tipo_vivienda"));
            casas.add(casa);
        }
        return casas;
    }

    public void eliminarCasaPorId(int id) throws Exception {
        String sql = "DELETE FROM casas WHERE id_casa = " + id;
        insertarModificarEliminarDataBase(sql);
    }

     // actualizarNombreEntidad
    public void actualizarCasa(Casa casa) throws Exception, SQLException, ClassNotFoundException {
        if (casa == null) {
            throw new Exception("La casa no puede ser nula");
        }

        String sql = "UPDATE casas SET calle = " + casa.getCalle() + ", numero = " + casa.getNumero() + ", codigo_postal = " + casa.getCodigoPostal() + ", ciudad = " + casa.getCiudad() + ", pais = " + casa.getPais() + ", fecha_desde = " + casa.getFechaDesde() + ", fecha_hasta = " + casa.getFechaHasta() + ", tiempo_minimo = " + casa.getTiempoMinimo() + ", tiempo_maximo = " + casa.getTiempoMaximo() + ", precio_habitacion = " + casa.getPrecioHabitacion() + ", tipo_vivienda = " + casa.getTipoVivienda() + " WHERE id_casa = " + casa.getIdCasa() + ";";

        insertarModificarEliminarDataBase(sql);
    }

     // buscarNombreEntidadPorId
     public Casa buscarCasaPorId(int id) throws Exception, SQLException, ClassNotFoundException {
        String sql = "SELECT * from casas where id_casa = " + id + ";";
        consultarDataBase(sql);
        Casa casaEncontrada = null;
        
        while (resultSet.next()) {
            casaEncontrada = crearCasa();
        }

        if (casaEncontrada == null) {
            System.out.printf("Casa con ID %s no encontrada", id);
        } else {
            System.out.println(casaEncontrada.toString());
        }

        return casaEncontrada;
    }

    private Casa crearCasa() throws SQLException {
        return new Casa(
                resultSet.getInt("id_casa"),
                resultSet.getString("calle"),
                resultSet.getInt("numero"),
                resultSet.getString("codigo_postal"),
                resultSet.getString("ciudad"),
                resultSet.getString("pais"),
                resultSet.getDate("fecha_desde").toLocalDate(),
                resultSet.getDate("fecha_hasta").toLocalDate(),
                resultSet.getInt("tiempo_minimo"),
                resultSet.getInt("tiempo_maximo"),
                resultSet.getDouble("precio_habitacion"),
                resultSet.getString("tipo_vivienda"));
    }

    public List<Casa> listarCasasDisponibles(LocalDate fechaInicial, LocalDate fechaFinal) throws Exception {
        String sql = "SELECT id_casa, calle, numero, ciudad, pais, precio_habitacion, tipo_vivienda FROM casas WHERE (fecha_desde <= '"
        + fechaInicial + "' AND fecha_hasta <= '" + fechaFinal + "') AND (id_casa NOT IN (SELECT id_casa FROM estancias"
        + " WHERE ('" + fechaInicial + "' <= fecha_hasta AND '" + fechaFinal + "' >= fecha_desde)))";
        consultarDataBase(sql);

        List<Casa> casas = new ArrayList<>();
        while (resultSet.next()) {
            Casa casa = new Casa();
            casa.setIdCasa(resultSet.getInt("id_casa"));
            casa.setCalle(resultSet.getString("calle"));
            casa.setNumero(resultSet.getInt("numero"));
            casa.setCiudad(resultSet.getString("ciudad"));
            casa.setPais(resultSet.getString("pais"));
            casa.setPrecioHabitacion(resultSet.getDouble("precio_habitacion"));
            casa.setTipoVivienda(resultSet.getString("tipo_vivienda"));
            casas.add(casa);
            System.out.println(casa.toString());
        }
        return casas;
    }
}
