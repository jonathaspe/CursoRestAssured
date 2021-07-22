package br.ce.wcaquino.rest;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OlaMundoTest {
    @Test
    public void testOlaMundo(){
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
        //System.out.println(response.getBody().asString().equals("Ola Mundo!"));
        //System.out.println(response.statusCode() == 200);

        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertEquals(200, response.statusCode());
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured(){
        given() //Pré-condições - Dado que...
        .when() //Ação - Quando...
                .get("https://restapi.wcaquino.me/ola")
        .then() //Assertivas - Então...
                .statusCode(200);

    }

    @Test
    public void devoConhecerMatchersHamcrest(){
        //A estrutura do Assert é => valor atual e o esperado
        Assert.assertThat("Maria", is("Maria"));
        Assert.assertThat("Maria", not("João"));
        Assert.assertThat("Maria", anyOf(is("Maria"), is("Joaquina")));
        Assert.assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("quina"), containsString("qui")));

        Assert.assertThat(128, is(128));
        Assert.assertThat("Maria", isA(String.class));
        Assert.assertThat(128, isA(Integer.class));
        Assert.assertThat(128d, greaterThan(120d));
        Assert.assertThat(128d, lessThan(130d));

        List<Integer> impares = Arrays.asList(1,3,5,7,9);
        Assert.assertThat(impares, hasSize(5));
        Assert.assertThat(impares, contains(1,3,5,7,9));
        Assert.assertThat(impares, containsInAnyOrder(3,1,7,9,5));
        Assert.assertThat(impares, hasItem(3));
    }

    @Test
    public void devoValidarBody(){
        given() //Pré-condições - Dado que...
        .when() //Ação - Quando...
                .get("https://restapi.wcaquino.me/ola")
        .then() //Assertivas - Então...
                .statusCode(200)
                .body(is("Ola Mundo!"))
                .body(containsString("Mundo"))
                .body(is(notNullValue()));
    }
}
