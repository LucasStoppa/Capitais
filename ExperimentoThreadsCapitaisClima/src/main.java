import java.io.IOException;
import java.util.List;

/**
 * A classe Principal contém o método main, que é o ponto de entrada do programa.
 * Ele executa o experimento para diferentes números de threads e calcula a média de tempo de execução.
 */
public class main {

    public static void main(String[] args) {
        try {
            // Obtém a lista de capitais brasileiras do arquivo local
            List<Capital> capitais = Utilitarios.lerCapitais("C:\\\\Users\\\\a\\\\Documents\\\\ExperimentoThreadsCapitaisClima\\\\Capitais.txt");

            // Arrays para armazenar os tempos de execução das diferentes versões do experimento
            long[] temposReferencia = new long[10];
            long[] tempos3Threads = new long[10];
            long[] tempos9Threads = new long[10];
            long[] tempos27Threads = new long[10];

            // Executa o experimento 10 vezes para cada versão
            for (int i = 0; i < 10; i++) {
                // Versão de Referência (1 thread)
                System.out.println("Rodada " + (i + 1) + " - Versão de Referência (1 thread)");
                long inicio = System.currentTimeMillis();
                Experimento.executar(1, capitais);
                long fim = System.currentTimeMillis();
                temposReferencia[i] = fim - inicio;

                // Versão com 3 Threads
                System.out.println("Rodada " + (i + 1) + " - Versão com 3 Threads");
                inicio = System.currentTimeMillis();
                Experimento.executar(3, capitais);
                fim = System.currentTimeMillis();
                tempos3Threads[i] = fim - inicio;

                // Versão com 9 Threads
                System.out.println("Rodada " + (i + 1) + " - Versão com 9 Threads");
                inicio = System.currentTimeMillis();
                Experimento.executar(9, capitais);
                fim = System.currentTimeMillis();
                tempos9Threads[i] = fim - inicio;

                // Versão com 27 Threads
                System.out.println("Rodada " + (i + 1) + " - Versão com 27 Threads");
                inicio = System.currentTimeMillis();
                Experimento.executar(27, capitais);
                fim = System.currentTimeMillis();
                tempos27Threads[i] = fim - inicio;
            }

            // Calcula a média de tempo de execução para cada versão do experimento
            System.out.println("\nMédia de Tempo de Execução (10 rodadas)");
            long mediaReferencia = calcularMedia(temposReferencia);
            long media3Threads = calcularMedia(tempos3Threads);
            long media9Threads = calcularMedia(tempos9Threads);
            long media27Threads = calcularMedia(tempos27Threads);

            // Exibe os resultados das médias de tempo de execução
            System.out.println("Versão de Referência (1 thread): " + mediaReferencia + " ms");
            System.out.println("Versão com 3 Threads: " + media3Threads + " ms");
            System.out.println("Versão com 9 Threads: " + media9Threads + " ms");
            System.out.println("Versão com 27 Threads: " + media27Threads + " ms");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calcula a média dos tempos de execução fornecidos.
     */
    private static long calcularMedia(long[] tempos) {
        long soma = 0;
        for (long tempo : tempos) {
            soma += tempo;
        }
        return soma / tempos.length;
    }
}
