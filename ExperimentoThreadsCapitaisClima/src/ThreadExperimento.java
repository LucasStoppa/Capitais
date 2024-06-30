import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 * A classe ThreadExperimento é responsável por realizar requisições HTTP e processar dados climáticos
 * para uma lista de capitais em uma thread separada.
 */
public class ThreadExperimento extends Thread {
    private List<Capital> capitais;

    public ThreadExperimento(List<Capital> capitais) {
        this.capitais = capitais;
    }

    @Override
    public void run() {
        for (Capital capital : capitais) {
            try {
                String dados = Utilitarios.obterDadosClimaticos(capital.getLatitude(), capital.getLongitude());
                Utilitarios.processarDados(dados, capital);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException ex) {
                Logger.getLogger(ThreadExperimento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
