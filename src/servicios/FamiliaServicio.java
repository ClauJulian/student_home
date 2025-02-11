package servicios;

import java.util.List;

import entidades.Familia;
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
}