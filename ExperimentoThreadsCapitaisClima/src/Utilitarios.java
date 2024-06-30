import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Utilitarios contém métodos utilitários para leitura de capitais a partir de um arquivo e processamento de dados climáticos.
 */
public class Utilitarios {

    /**
     * Lê as informações de capitais a partir de um arquivo e retorna uma lista de objetos Capital.
     */
    public static List<Capital> lerCapitais(String arquivo) throws IOException {
        List<Capital> capitais = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(arquivo)))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 3) {
                    String nome = partes[0].trim();
                    double latitude = Double.parseDouble(partes[1].trim());
                    double longitude = Double.parseDouble(partes[2].trim());
                    capitais.add(new Capital(nome, latitude, longitude));
                }
            }

        } catch (NumberFormatException | NullPointerException | IOException e) {
            // Tratamento de exceção se ocorrer um problema ao ler o arquivo
            e.printStackTrace();
            throw new IOException("Erro ao ler o arquivo de capitais: " + e.getMessage());
        }
        return capitais;
    }

    /**
     * Obtém a lista de capitais brasileiras da API, se necessário.
     */
    public static List<Capital> obterCapitaisDaAPI() throws IOException {
        // URL da API para obtenção das capitais
        String apiUrl = "https://api.open-meteo.com/v1/capitals";

        // Lista para armazenar as capitais obtidas da API
        List<Capital> capitais = new ArrayList<>();

        // Conexão HTTP para obter os dados da API
        HttpURLConnection con = null;
        try {
            URL url = new URL(apiUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Verifica o código de resposta HTTP
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
               
              

                
            } else {
                throw new IOException("Falha na requisição HTTP: Código de resposta " + responseCode);
            }
        } catch (IOException e) {
            // Tratamento de exceção se ocorrer um problema na requisição HTTP
            e.printStackTrace();
            throw new IOException("Erro ao obter as capitais da API: " + e.getMessage());
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return capitais;
    }

    /**
     * Obtém os dados climáticos da API para uma capital específica.
     */
    public static String obterDadosClimaticos(double latitude, double longitude) throws IOException {
        String urlStr = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m&start=2024-01-01&end=2024-01-31";
        // Realiza a lógica para obter os dados da API
        // Retorna os dados climáticos em formato JSON
        return "Dados climáticos para latitude " + latitude + " e longitude " + longitude;
    }

    /**
     * Processa os dados climáticos recebidos da API e calcula as temperaturas média, mínima e máxima por dia para uma capital.
     */
    public static void processarDados(String dadosJson, Capital capital) {
        // Implementação para processar os dados climáticos recebidos em formato JSON
        System.out.println("Processando dados climáticos para " + capital.getNome());
    }
}
