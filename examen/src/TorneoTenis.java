import java.util.*;
import java.util.concurrent.*;

public class TorneoTenis {
    public static void main(String[] args) throws Exception {
        List<String> jugadores = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            jugadores.add("Jugador " + i);
        }

        ExecutorService executor = Executors.newFixedThreadPool(8);

        jugadores = simularFase("OCTAVOS DE FINAL", jugadores, executor);
        jugadores = simularFase("CUARTOS DE FINAL", jugadores, executor);
        jugadores = simularFase("SEMIFINAL", jugadores, executor);
        jugadores = simularFase("FINAL", jugadores, executor);

        System.out.println("\uD83C\uDFC6 ¡Campeón del torneo: " + jugadores.get(0) + "!");

        executor.shutdown();
    }

    public static List<String> simularFase(String nombreFase, List<String> jugadores, ExecutorService executor) throws Exception {
        System.out.println("===== " + nombreFase + " =====");

        List<Future<List<String>>> resultados = new ArrayList<>();
        List<String> ganadores = new ArrayList<>();

        int n = jugadores.size();
        for (int i = 0; i < n / 2; i++) {
            String jugador1 = jugadores.get(i);
            String jugador2 = jugadores.get(n - 1 - i);
            resultados.add(executor.submit(new Partido(jugador1, jugador2)));
        }

        for (Future<List<String>> resultado : resultados) {
            List<String> infoPartido = resultado.get();
            for (String linea : infoPartido) {
                System.out.println(linea);
            }
            ganadores.add(infoPartido.get(infoPartido.size() - 1).replace("Ganador del partido: ", "").trim());
        }

        return ganadores;
    }
}