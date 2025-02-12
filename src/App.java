
import java.time.LocalDate;
import java.util.Scanner;
import servicios.CasaServicio;
import servicios.ClienteServicio;
import servicios.FamiliaServicio;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);


        FamiliaServicio familiaServicio = new FamiliaServicio();
        CasaServicio casaServicio = new CasaServicio();
        ClienteServicio clienteServicio = new ClienteServicio();

        String menu = """
                ### Elige tu opción:

                1. Listar familias con al menos 3 hijos, y con edad máxima inferior a 10 años. 
                2. Listar casas disponibles entre el 01-08-2020 y el 31-08-2020 en Reino Unido.
                3. Listar familias cuya dirección de email sea Hotmail.  
                4. Consulta la BD para que te devuelva aquellas casas disponibles a partir de una fecha dada y un número de días específico. 
                5. Listar los clientes que en algún momento realizaron una estancia y la descripción de la casa donde la realizaron. 
                6. Listar las estancias que han sido reservadas por un cliente, mostrar el nombre, país y ciudad del cliente y además la información de la casa que reservó. La que reemplazaría a la anterior 
                7. Crear un método para incrementar el precio por día. El mismo debe recibir como parámetro el % de aumento. En esta ocasión, debido a la devaluación de la libra esterlina con respecto al euro se desea incrementar el precio por día en un 5% de todas las casas del Reino Unido. Mostrar los precios actualizados. 
                8. Obtener el número de casas que existen para cada uno de los países diferentes.
                9. Buscar y listar aquellas casas del Reino Unido de las que se ha dicho de ellas (comentarios) que están ‘limpias’. 
                10. Insertar nuevos datos en la tabla estancias verificando la disponibilidad de las fechas. 

                0. Salir

                """;

        int opcion = 1;

        while (opcion != 0) {
            System.out.println(menu);
            opcion = scanner.nextInt();
        

            switch (opcion) {
                case 1 : familiaServicio.listarMinHijosEdadesMaximas(3, 10);
                    break;

                case 2 : casaServicio.listarTodasLasCasasDisponibles("reino unido", LocalDate.parse("2020-08-01"), LocalDate.parse("2020-08-31"));
                    break;

                case 3: familiaServicio.listarFamiliaHotmail();

                    break;

                case 4: System.out.print("Ingrese la fecha desde: ");
                        String fecha = scanner.next();
                        System.out.print("Ingrese la cantidad de dias: ");
                        int dias = scanner.nextInt();
                        casaServicio.casasDisponiblesApartirDe(fecha, dias);
                break;
                case 5: clienteServicio.listarClientesConEstancias();
                    break; 
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;    
                case 9:
                    break;    
                case 10:
                    break;
                case 0:
                    break;    
                default:
                    throw new AssertionError();
            }
        }

    }
}
