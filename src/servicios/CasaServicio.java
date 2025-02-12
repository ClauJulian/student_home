package servicios;

import entidades.Casa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<Casa> listarTodasLasCasasDisponibles(String pais, LocalDate fechaInicial, LocalDate fechaFinal) throws Exception {

        List<Casa> todas_casas = casaDAO.listarTodasLasCasas();
        List<Casa> casas_periodo_pais = new ArrayList<>();

        validarListarCasasDisponibles(fechaInicial, fechaFinal);

        for (Casa casa : todas_casas) {

            if((casa.getPais()).equalsIgnoreCase(pais)){

                System.out.println("Fecha desde : "+ casa.getFechaDesde());

             if(fechaInicial.isAfter(casa.getFechaDesde()) && fechaFinal.isBefore(casa.getFechaHasta())){
                casas_periodo_pais.add(casa);
            }
            
            casas_periodo_pais.add(casa);
            System.out.println(casa.toString());
        }

        
        }
        return casas_periodo_pais;
    }

     public void casasDisponiblesApartirDe(String fecha, int cantidadDias) throws ParseException {
        try {
            List<Casa> casasCompletas = casaDAO.listarTodasLasCasas();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //LocalDate fechaDesde = sdf.parse(fecha);

            for (Casa casa : casasCompletas) {
                if( (casa.getFechaDesde().isAfter(LocalDate.parse(fecha))) && (casa.getTiempoMaximo()>=cantidadDias)){
                    System.out.println(casa.toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

