import java.util.ArrayList;
import java.util.List;

/**
 * A classe Experimento executa o experimento de coleta e processamento de dados climáticos
 * usando diferentes números de threads.
 */
public class Experimento {

    /**
     * Executa o experimento usando um determinado número de threads para processar a lista de capitais.
     */
    public static void executar(int numeroDeThreads, List<Capital> capitais) {
        int numCapitaisPorThread = (int) Math.ceil((double) capitais.size() / numeroDeThreads);
        List<ThreadExperimento> threads = new ArrayList<>();

        for (int i = 0; i < numeroDeThreads; i++) {
            int inicio = i * numCapitaisPorThread;
            int fim = Math.min(inicio + numCapitaisPorThread, capitais.size());
            List<Capital> subLista = capitais.subList(inicio, fim);
            ThreadExperimento thread = new ThreadExperimento(subLista);
            threads.add(thread);
        }

        long inicio = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (fim - inicio) + " ms");
    }
}
