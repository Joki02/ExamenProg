import java.util.*;
import java.util.concurrent.Callable;

public class Partido implements Callable<List<String>> {
    private final String jugador1;
    private final String jugador2;
    private final Random random = new Random();

    public Partido(String jugador1, String jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> resultado = new ArrayList<>();
        resultado.add(jugador1 + " vs " + jugador2);

        int setsJugador1 = 0;
        int setsJugador2 = 0;

        for (int i = 1; i <= 2; i++) {
            String ganadorSet = random.nextBoolean() ? jugador1 : jugador2;
            resultado.add("Set " + i + ": " + ganadorSet);
            if (ganadorSet.equals(jugador1)) setsJugador1++;
            else setsJugador2++;
        }

        if (setsJugador1 == setsJugador2) {
            String ganadorSet3 = random.nextBoolean() ? jugador1 : jugador2;
            resultado.add("Set 3: " + ganadorSet3);
            if (ganadorSet3.equals(jugador1)) setsJugador1++;
            else setsJugador2++;
        }

        String ganadorPartido = setsJugador1 > setsJugador2 ? jugador1 : jugador2;
        resultado.add("Ganador del partido: " + ganadorPartido + "\n");

        Thread.sleep(1500 + random.nextInt(501)); // entre 1.5 y 2 segundos

        return resultado;
    }
}
