/**
 * @author Adelino Fernandes
 * @version 1.0
 */

import io.restassured.response.Response;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static io.restassured.RestAssured.*;


public class TrelloTestes {

    private static Trello trello = new Trello();

    /**
     * Variáveis de apoio aos testes. Cabe salientar que a execução isolada de
     * alguns métodos pode causar instabilidades nas variáveis, portanto sugiro
     * executar os testes através do método fluxoDeExecucao().
     */
    String id;
    ArrayList<String> ids_lists = new ArrayList<String>();
    ArrayList<String> ids_cards = new ArrayList<String>();


    @BeforeClass
    public static void caminhoPrefixo () {
        baseURI = trello.getCaminho();
    }

    /**
     * Para criar criar os cartões faz-se-á necessária a criação
     * de um quadro, logo, para testar as demais funcionalidades
     * basta que o testador possua o ID de um quadro qualquer.
     *
     * Neste caso, chamaremos a função de criarBoard para automatizar
     * o fluxo e colher a informação sobre o ID, trabalhando com ele
     * até a destruição de todos os cartões e quadro.
     */
    @Test
    public void criarBoard() {
        Response response =
                (Response) given().contentType("application/json")
                        .queryParams("key",trello.getKey())
                        .queryParams("token",trello.getToken())
                        .queryParams("name","Testes da API do Trello")
                        .when().post("boards/");
                        response.then()
                        .statusCode(200)
                        .log().all();
                       id = response.jsonPath().getString("id");
    }

    /**
     * Método a ser usado para apagar o quadro ao fim de
     * do processo, pois a quantidade de quadros na versão
     * gratuita é limitada.
     */
    @Test
    public void deletarBoard() {
        given().contentType("application/json")
                .queryParams("key", trello.getKey())
                .queryParams("token", trello.getToken())
                .when()
                .delete("boards/" + id + "/")
                .then()
                .statusCode(200)
                .log().all();
    }

    /**
     * Método responsável por colher os identificadores das
     * raias em lista, os identificadores serão usados para
     * trabalhar com os cartões.
     */
    @Test
    public void getList() {
        Response response =
                (Response) given().contentType("application/json")
                        .queryParams("key",trello.getKey())
                        .queryParams("token",trello.getToken())
                        .when()
                        .get("boards/"+id+"/lists");
                        response.then().log().all();
                        ids_lists.addAll(Arrays.asList(response.jsonPath().getString("id").replace("[","").replace("]","").replace(" ","").split(",")));
    }

    /**
     * Método responsável por criar um cartão na primeira lista.
     */
    @Test
    public void criarCard() {
        given().contentType("application/json")
                .queryParams("key",trello.getKey())
                .queryParams("token",trello.getToken())
                .queryParams("idList", ids_lists.get(0))
                .queryParams("name","Criando um Card")
                .when()
                .post("cards/")
                .then()
                .statusCode(200)
                .log().all();
    }

    /**
     * Método responsável por colher os identificadores
     * dos cartões para que seja possível editar ou deslocar
     * cada cartão entre as listas.
     */
    @Test
    public void getCards() {
        Response response =
                (Response) given().contentType("application/json")
                        .queryParams("key",trello.getKey())
                        .queryParams("token",trello.getToken())
                        .when()
                        .get("boards/"+id+"/cards");
        response.then().log().all();
        ids_cards.addAll(Arrays.asList(response.jsonPath().getString("id").replace("[","").replace("]","").replace(" ","").split(",")));
    }

    /**
     * Método para editar dados de um cartão, não foram testadas
     * todas as possíveis edições, pois duas delas já são suficientes
     * para validar o solicitado.
     */
    @Test
    public void editarCard() {
        given().contentType("application/json")
                .queryParams("key", trello.getKey())
                .queryParams("token", trello.getToken())
                .queryParam("name","Card Editado")
                .queryParam("closed","true")
                .when()
                .put("cards/" + ids_cards.get(0))
                .then()
                .statusCode(200)
                .log().all();
    }

    /**
     * Método responsável por deslocar todos os cartões de uma
     * lista para a lista que a sucede.
     */
    @Test
    public void moverCards() {
        for (int i = 0; i < ids_cards.size(); i++) {
            given().contentType("application/json")
                    .queryParams("key", trello.getKey())
                    .queryParams("token", trello.getToken())
                    .queryParams("idList", (ids_lists.size() > 1)?ids_lists.get(1):ids_lists.get(0))
                    .queryParam("name","Card Movido")
                    .when()
                    .put("cards/" + ids_cards.get(i))
                    .then()
                    .statusCode(200)
                    .log().all();
        }
    }

    /**
     * Método responsável por apagar todos os cartões criados.
     */
    @Test
    public void apagarCards() {
        for (int i = 0; i < 1; i++) {
            given().contentType("application/json")
                    .when()
                    .delete("cards/" + ids_cards.get(i))
                    .then()
                    .log().all();
       }
        ids_cards.clear();
    }


    /**
     * Neste método é feita a execução do fluxo completo, sendo necessário mudanças
     * nos parâmetros caso queira executar os métodos isolados.
     */
    @Test
    public void fluxoDeExecucao() {
        criarBoard();
        getList();
        criarCard();
        criarCard();
        getCards();
        editarCard();
        moverCards();
        apagarCards();
    }

    @After
    public void limparDados() {
        deletarBoard();
    }
}
