package persistencia;

import entidades.Familia;
import java.util.ArrayList;
import java.util.List;

public class FamiliaDAO extends DAO{

    public void guardarFamilia(Familia familia) throws Exception {

        if (familia == null) {
          throw new Exception("Familia no puede ser nulo");
        } else {


            String sql =
            "INSERT INTO familias (nombre, edad_minima, edad_maxima, num_hijos, email, id_casa_familia)"
                + " VALUES ('"
                + familia.getNombre()
                + "', '"
                + familia.getEdadMinima()
                + "','"
                + familia.getEdadMaxima()
                + "','"
                + familia.getNumHijos()
                + "','"
                + familia.getEmail()
                + "','"
                + familia.getIdCasaFamilia()
                + "')";
  
        insertarModificarEliminarDataBase(sql);   
        
    }
    }

    public List<Familia> listarTodasLasFamilias() throws Exception {

        String sql =
            "SELECT * FROM familias";
        consultarDataBase(sql);
        List<Familia> familias = new ArrayList<>();
        while (resultSet.next()) {
          Familia familia = new Familia();
          familia.setIdFamilia(resultSet.getInt("id_familia"));
          familia.setNombre(resultSet.getString("nombre"));
          familia.setEdadMinima(resultSet.getInt("edad_minima"));
          familia.setEdadMaxima(resultSet.getInt("edad_maxima"));
          familia.setNumHijos(resultSet.getInt("num_hijos"));
          familia.setEmail(resultSet.getString("email"));
          familia.setIdCasaFamilia(resultSet.getInt("id_casa_familia"));
          familias.add(familia);
          //System.out.println(familia.toString());
          //System.out.println(" ");
        }
        return familias;
      }

      public Familia buscarFamiliaPorId(int id) throws Exception {
        String sql = "SELECT * FROM familias WHERE id_familia = " + id;
        consultarDataBase(sql);
    
        Familia familia = new Familia();
        if (resultSet.next()) {
          familia.setIdFamilia(resultSet.getInt("id_familia"));
          familia.setNombre(resultSet.getString("nombre"));
          familia.setEdadMinima(resultSet.getInt("edad_minima"));
          familia.setEdadMaxima(resultSet.getInt("edad_maxima"));
          familia.setNumHijos(resultSet.getInt("num_hijos"));
          familia.setEmail(resultSet.getString("email"));
          familia.setIdCasaFamilia(resultSet.getInt("id_casa_familia"));
         
          //System.out.println("Esta es la familia buscada: " + familia.toString());
          

        }
        return familia; 
      }
    
      public void eliminarFamilia(int idFamilia) throws Exception {
        String sql = "DELETE FROM familias WHERE id_familia = " + idFamilia;
    
        insertarModificarEliminarDataBase(sql);
      }

      public List<Familia> listarMinHijosEdadesMaximas(int minHijos, int edadMaxima) throws Exception {

        String sql = "SELECT * FROM familias WHERE num_hijos >= " + minHijos + " AND edad_maxima < " + edadMaxima;
        consultarDataBase(sql);
        List<Familia> familias = new ArrayList<>();
        while (resultSet.next()) {
            Familia familia = new Familia();
            familia.setIdFamilia(resultSet.getInt("id_familia"));
            familia.setNombre(resultSet.getString("nombre"));
            familia.setEdadMinima(resultSet.getInt("edad_minima"));
            familia.setEdadMaxima(resultSet.getInt("edad_maxima"));
            familia.setNumHijos(resultSet.getInt("num_hijos"));
            familia.setEmail(resultSet.getString("email"));
            familia.setIdCasaFamilia(resultSet.getInt("id_casa_familia"));
            familias.add(familia);
            System.out.println(familia.toString());
            System.out.println(" ");
        }
        return familias;
    }


      
}

