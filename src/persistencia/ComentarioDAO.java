package persistencia;

import entidades.Comentario;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO extends DAO {
    
    public void guardarComentario(Comentario comentario) throws Exception {
        if (comentario == null) {
            throw new Exception("El comentario no puede ser nulo");
        }
        String sql = "INSERT INTO comentarios (id_casa, comentario) VALUES ("
                + comentario.getIdCasa() + ", '"
                + comentario.getComentario() + "')";
        insertarModificarEliminarDataBase(sql);
    }

    public List<Comentario> listarTodosLosComentarios() throws Exception {
        String sql = "SELECT id_comentario, id_casa, comentario FROM comentarios";
        consultarDataBase(sql);

        List<Comentario> comentarios = new ArrayList<>();
        while (resultSet.next()) {
            Comentario comentario = new Comentario();
            comentario.setIdComentario(resultSet.getInt("id_comentario"));
            comentario.setIdCasa(resultSet.getInt("id_casa"));
            comentario.setComentario(resultSet.getString("comentario"));
            comentarios.add(comentario);
        }
        return comentarios;
    }

    public void eliminarComentarioPorId(int id) throws Exception {
        String sql = "DELETE FROM comentarios WHERE id_comentario = " + id;
        insertarModificarEliminarDataBase(sql);
    }

    public Comentario buscarComentarioPorId(int id) throws Exception {
        String sql = "SELECT * FROM comentarios WHERE id_comentario = " + id;
        consultarDataBase(sql);
        resultSet.next();
        Comentario comentario = crearComentario();
        desconectarDataBase();
        System.out.println(comentario.toString());
        return comentario;
    }

    public Comentario crearComentario() throws Exception {
        Comentario comentario = new Comentario();
        comentario.setIdComentario(resultSet.getInt("id_comentario"));
        comentario.setIdCasa(resultSet.getInt("id_casa"));
        comentario.setComentario(resultSet.getString("comentario"));
        return comentario;
    }
}
