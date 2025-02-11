package servicios;

import entidades.Casa;
import java.time.LocalDate;
import java.util.List;
import persistencia.CasaDAO;

public class CasaServicio {
    private CasaDAO casaDAO;

    public CasaServicio() {
        casaDAO = new CasaDAO();
    }

    public List<Casa> listarCasasDisponibles(LocalDate fechaInicial, LocalDate fechaFinal) throws Exception {
        validarListarCasasDisponibles(fechaInicial, fechaFinal);
        return casaDAO.listarCasasDisponibles(fechaInicial, fechaFinal);
    }

    private void validarListarCasasDisponibles(LocalDate fechaInicial, LocalDate fechaFinal) throws Exception {
        if (fechaInicial == null || fechaFinal == null) {
            throw new Exception("Fechas no pueden ser nulas");
        }
        if (fechaFinal.isBefore(fechaInicial)) {
            throw new Exception("Fecha final no puede ser menor a fecha inicial");
        }
    }
}

