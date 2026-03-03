package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URI = "https://stellarburgers.education-services.ru";

    @Step("Создание тестового пользователя через API")
    public Response createUser(User user) {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(String.format(
                        "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                        user.getEmail(), user.getPassword(), user.getName()))
                .when()
                .post("/api/auth/register");
    }

    @Step("Логин тестового пользователя через API")
    public String loginUser(User user) {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(String.format(
                        "{\"email\":\"%s\",\"password\":\"%s\"}",
                        user.getEmail(), user.getPassword()))
                .when()
                .post("/api/auth/login")
                .then()
                .extract()
                .path("accessToken");
    }

    @Step("Удаление тестового пользователя через API")
    public void deleteUser(String accessToken) {
        given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}
