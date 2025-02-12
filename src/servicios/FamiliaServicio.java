package servicios;

import entidades.Familia;
import java.util.ArrayList;
import java.util.List;
import persistencia.FamiliaDAO;

public class FamiliaServicio {
    private FamiliaDAO familiaDAO;

    public FamiliaServicio() {
        familiaDAO = new FamiliaDAO();
    }

    public List<Familia> listarMinHijosEdadesMaximas(int cantidadHijos, int edadMaxima) throws Exception {
        validarListarMinHijosEdadesMaximas(cantidadHijos, edadMaxima);
        return familiaDAO.listarMinHijosEdadesMaximas(cantidadHijos, edadMaxima);
    }

    private void validarListarMinHijosEdadesMaximas(int cantidadHijos, int edadMaxima) throws Exception {
        if (cantidadHijos < 0 || edadMaxima < 0) {
            throw new Exception("Cantidad de hijos y edad máxima no pueden ser negativos");
        }
    }

    public List<Familia> listarFamiliaHotmail() throws Exception {
        
        List<Familia> familiasHotmail = new ArrayList<>();
        List<Familia> todas_familias =familiaDAO.listarTodasLasFamilias();

        for (Familia familia : todas_familias) {
           if(esHotmail(familia.getEmail())){
            System.out.println(familia.toString());
            familiasHotmail.add(familia);
           }
        }
        return familiasHotmail;
    }

    public static boolean esHotmail(String correo) {
        String[] partes = correo.split("@");
        if (partes.length == 2) {
            String dominio = partes[1].toLowerCase();
            return dominio.startsWith("hotmail.");
        }
        return false;
    }
}