import java.util.ArrayList;

/**
 * @author Adelino Fernandes
 * @version 1.0
 *
 * Classe criada como fachada para as autenticações necessárias.
 */

public class Trello {
    /**
     * Variáveis de apoio aos testes. Cabe salientar que a execução isolada de
     * alguns métodos pode causar instabilidades nas variáveis, portanto sugiro
     * executar os testes através do NAME_ASCENDING anotado na classe TrelloTest.
     */
    private static String caminho;
    private static String key = "96849ab24b3fcc99d993520f97689c3c";
    private static String token = "09f188c63b6752f3aea035b36e35a9047e4d2573c063612ef7a2d2f510d53961";
    public static String id;
    public static ArrayList<String> ids_lists = new ArrayList<String>();
    public static ArrayList<String> ids_cards = new ArrayList<String>();

    public static String getCaminho() {
        caminho = "https://api.trello.com/1/";
        return caminho;
    }

    public static String getKey() {
        return key;
    }

    public static String getToken() {
        return token;
    }
}
