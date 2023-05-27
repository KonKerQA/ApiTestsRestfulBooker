package com.restfulbooker.test;

import com.restfulbooker.function.AuthFunction;
import com.restfulbooker.function.CreateNewBookingFunction;
import com.restfulbooker.models.booking.BookerModel;
import com.restfulbooker.models.login.LoginModelResponse;
import com.restfulbooker.models.booking.NewBookerModel;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static com.restfulbooker.helper.RequestConverter.convertFileToString;
import static com.restfulbooker.specs.JsonSpec.jsonSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RestfulBookerAPITests extends BaseTest {


    @Test
    @Epic("Авторизация")
    @Description("Получение токена авторизации")
    public void authTest() throws IOException {
        String req = convertFileToString("request/auth.json");

        step("Отправляем запрос на получение токена авторизации");
        LoginModelResponse loginModelResponse = given()
                .config(config)
                .spec(jsonSpec)
                .body(req)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().as(LoginModelResponse.class);
        assertThat(loginModelResponse.getToken()).isNotNull();
    }

    @Test
    @Epic("Получение информации о созданном букинге")
    @Description("Получение информации о созданном букинге")
    void getBooking() throws IOException {
        step("Отправляем запрос на создание букинга");
        int bookingId = CreateNewBookingFunction.createBooking();

        step("Отправляем запрос на получение информации о созданом букинге");
        BookerModel bookerModel = given()
                .config(config)
                .when()
                .get("booking/" + bookingId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(BookerModel.class);

        assertThat(bookerModel.getFirstname()).isEqualTo("Stellio");
        assertThat(bookerModel.getLastname()).isEqualTo("Costo");
        assertThat(bookerModel.getTotalprice()).isEqualTo(223);
        assertThat(bookerModel.isDepositpaid()).isEqualTo(true);
        assertThat(bookerModel.getBookingdates().getCheckin()).isEqualTo("2020-01-01");
        assertThat(bookerModel.getBookingdates().getCheckout()).isEqualTo("2021-01-01");
        assertThat(bookerModel.getAdditionalneeds()).isEqualTo("vodka");
    }


    @Test
    @Epic("Получение айдишников всех букингов")
    @Description("Получение айдишников всех букингов")
    void getAllBookingIds() {

        given()
                .config(config)
                .when()
                .get("/booking")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test
    @Epic("Получение айдишников всех букингов")
    @Description("Получение айдишников всех букингов")
    void getInvalidBookingIds() {
        long invalidId = Instant.now().getEpochSecond();
        given()
                .config(config)
                .when()
                .get("/booking/" + invalidId)
                .then()
                .log().body()
                .statusCode(404);
    }

    @Test
    @Epic("Создание букинга")
    @Description("Создание букинга")
    void createBooking() throws IOException {
        String req = convertFileToString("request/newBooking.json");

        step("Отправляем запрос на создание букинга");
        NewBookerModel newbookerModel = given()
                .config(config)
                .spec(jsonSpec)
                .body(req)
                .when()
                .post("/booking")
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(NewBookerModel.class);

        assertThat(newbookerModel.getBookingid()).isNotNull();
        assertThat(newbookerModel.getBooking().getFirstname()).isEqualTo("Stellio");
        assertThat(newbookerModel.getBooking().getLastname()).isEqualTo("Costo");
        assertThat(newbookerModel.getBooking().getTotalprice()).isEqualTo(223);
        assertThat(newbookerModel.getBooking().isDepositpaid()).isEqualTo(true);
        assertThat(newbookerModel.getBooking().getBookingdates().getCheckin()).isEqualTo("2020-01-01");
        assertThat(newbookerModel.getBooking().getBookingdates().getCheckout()).isEqualTo("2021-01-01");
        assertThat(newbookerModel.getBooking().getAdditionalneeds()).isEqualTo("vodka");
    }


    @Test
    @Epic("Изменение всех параметров букинга")
    @Description("Изменение всех парметров букинга")
    void updateBooking() throws IOException {
        step("Отправляем запрос на получение токена авторизации");
        String token = AuthFunction.getAuthToken().getToken();
        System.out.println(token);

        step("Отправляем запрос на создание букинга");
        int bookingId = CreateNewBookingFunction.createBooking();

        String req = convertFileToString("request/updateBooking.json");

        step("Отправляем запрос на изменение всех параметров букинга");
        BookerModel bookerModel = given()
                .config(config)
                .spec(jsonSpec)
                .cookie("token", token)
                .body(req)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(BookerModel.class);

        assertThat(bookerModel.getFirstname()).isEqualTo("Stellio");
        assertThat(bookerModel.getLastname()).isEqualTo("Costo");
        assertThat(bookerModel.getTotalprice()).isEqualTo(144);
        assertThat(bookerModel.isDepositpaid()).isEqualTo(false);
        assertThat(bookerModel.getBookingdates().getCheckin()).isEqualTo("2022-01-01");
        assertThat(bookerModel.getBookingdates().getCheckout()).isEqualTo("2023-01-01");
        assertThat(bookerModel.getAdditionalneeds()).isEqualTo("lolkek");
    }


    @Test
    @Epic("Частичное изменение параметров букинга")
    @Description("Частичное изменение параметров букинга")
    void partialUpdateBooking() throws IOException {
        step("Отправляем запрос на получение токена авторизации");
        String token = AuthFunction.getAuthToken().getToken();
        System.out.println(token);

        step("Отправляем запрос на создание букинга");
        int bookingId = CreateNewBookingFunction.createBooking();

        String req = convertFileToString("request/partialUpdateBooking.json");

        step("Отправляем запрос на частичное изменение параметров букинга");
        BookerModel bookerModel = given()
                .config(config)
                .spec(jsonSpec)
                .cookie("token", token)
                .body(req)
                .when()
                .patch("/booking/" + bookingId)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(BookerModel.class);

        assertThat(bookerModel.getFirstname()).isEqualTo("David");
        assertThat(bookerModel.getLastname()).isEqualTo("Trad");
        assertThat(bookerModel.getTotalprice()).isEqualTo(223);
        assertThat(bookerModel.isDepositpaid()).isEqualTo(true);
        assertThat(bookerModel.getBookingdates().getCheckin()).isEqualTo("2020-01-01");
        assertThat(bookerModel.getBookingdates().getCheckout()).isEqualTo("2021-01-01");
        assertThat(bookerModel.getAdditionalneeds()).isEqualTo("vodka");
    }


    @Test
    @Epic("Получение айдишников всех букингов")
    @Description("Получение айдишников всех букингов")
    void deleteBooking() throws IOException {

        step("Отправляем запрос на получение токена авторизации");
        String token = AuthFunction.getAuthToken().getToken();
        System.out.println(token);

        step("Отправляем запрос на создание букинга");
        int bookingId = CreateNewBookingFunction.createBooking();

        String req = convertFileToString("request/partialUpdateBooking.json");

        step("Отправляем запрос на удаления букинга");
        given()
                .config(config)
                .spec(jsonSpec)
                .cookie("token", token)
                .body(req)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .log().body()
                .statusCode(201);
    }


}

