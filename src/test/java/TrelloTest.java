/**
 * @author Adelino Fernandes
 * @version 1.0
 */

import io.restassured.response.Response;
import junitparams.Parameters;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.*;

@RunWith(junitparams.JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrelloTest {

    @BeforeClass
    public static void caminhoPrefixo () {
        baseURI = Trello.getCaminho();
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
    @Parameters(value = {"Testes API",""})
    public void a_criarBoard(String nome) {
        Response response =
                (Response) given().contentType("application/json")
                        .queryParams("key",Trello.getKey())
                        .queryParams("token",Trello.getToken())
                        .queryParams("name",nome)
                        .when().post("boards/");
                        response.then()
                        .statusCode(200)
                        .log().all();
                       Trello.id = response.jsonPath().getString("id");
    }

    /**
     * Método a ser usado para apagar o quadro ao fim de
     * do processo, pois a quantidade de quadros na versão
     * gratuita é limitada.
     */
    @Test
    public void h_deletarBoard() {
        given().contentType("application/json")
                .queryParams("key", Trello.getKey())
                .queryParams("token", Trello.getToken())
                .when()
                .delete("boards/" + Trello.id + "/")
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
    public void b_getList() {
        Response response =
                (Response) given().contentType("application/json")
                        .queryParams("key",Trello.getKey())
                        .queryParams("token",Trello.getToken())
                        .when()
                        .get("boards/"+Trello.id+"/lists");
                        response.then().log().all();
                        Trello.ids_lists.addAll(Arrays.asList(response.jsonPath().getString("id").replace("[","").replace("]","").replace(" ","").split(",")));
    }

    /**
     * Método responsável por criar um cartão na primeira lista.
     */
    @Test
    @Parameters(value = {"Card 1", "Card 2",""})
    public void c_criarCard(String nome) {
        given().contentType("application/json")
                .queryParams("key",Trello.getKey())
                .queryParams("token",Trello.getToken())
                .queryParams("idList",  Trello.ids_lists.get(0))
                .queryParams("name",nome)
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
    public void d_getCards() {
        Response response =
                (Response) given().contentType("application/json")
                        .queryParams("key",Trello.getKey())
                        .queryParams("token",Trello.getToken())
                        .when()
                        .get("boards/"+ Trello.id+"/cards");
        response.then().log().all();
        Trello.ids_cards.addAll(Arrays.asList(response.jsonPath().getString("id").replace("[","").replace("]","").replace(" ","").split(",")));
    }

    /**
     * Método para editar dados de um cartão, não foram testadas
     * todas as possíveis edições, pois duas delas já são suficientes
     * para validar o solicitado.
     */
    @Test
    @Parameters("Card Editado")
    public void e_editarCard(String nome) {
        given().contentType("application/json")
                .queryParams("key", Trello.getKey())
                .queryParams("token", Trello.getToken())
                .queryParam("name",nome)
                .queryParam("closed","true")
                .when()
                .put("cards/" +  Trello.ids_cards.get(1))
                .then()
                .statusCode(200)
                .log().all();
    }

    /**
     * Método responsável por deslocar todos os cartões de uma
     * lista para a lista que a sucede.
     */
    @Test
    public void f_moverCards() {
        for (int i = 0; i <  Trello.ids_cards.size(); i++) {
            given().contentType("application/json")
                    .queryParams("key", Trello.getKey())
                    .queryParams("token", Trello.getToken())
                    .queryParams("idList", ( Trello.ids_lists.size() > 1)? Trello.ids_lists.get(1): Trello.ids_lists.get(0))
                    .when()
                    .put("cards/" +  Trello.ids_cards.get(i))
                    .then()
                    .statusCode(200)
                    .log().all();
        }
    }

    /**
     * Método responsável por apagar todos os cartões criados.
     */
    @Test
    @RepeatedTest(1)
    public void g_apagarCards() {
        for (int i = 0; i < 1; i++) {
            given().contentType("application/json")
                    .when()
                    .delete("cards/" +  Trello.ids_cards.get(i))
                    .then()
                    .log().all();
       }
        Trello.ids_cards.clear();
    }
}
