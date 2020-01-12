import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestClass {
    private SelenideElement form;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    @DisplayName("Тестирование формы 'Карта с доставкой'")
    void testFormIfValidateAllInputData() {
        openForm();
        inputValidData();
        inputDay(10);
        $("[data-test-id=success-notification]").shouldBe(visible);
    }

    @Step("Запуск формы 'Карта с доставкой'")
    private void openForm() {
        open("http://localhost:9999");
        form = $(By.className("App_appContainer__3jRx1"));
    }

    @Step("Авторизация корректными данными")
    private void inputValidData() {
        form.$("[data-test-id=city] input").setValue(UserModel.getCity());
        form.$("[data-test-id=name] input").setValue(UserModel.getName());
        form.$("[data-test-id=phone] input").setValue(UserModel.getPhone());
        form.$("[data-test-id=agreement]").click();
    }

    @Step("Ввод и изменение даты доставки карты")
    private void inputDay(int plusDay) {
        form.$$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible);
        $(By.xpath("//*[@id='root']/div/div[1]/button")).click();
        LocalDate otherDay = LocalDate.now().plusDays(plusDay);
        String futureDay = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(otherDay);
        form.$("[placeholder='Дата встречи']").sendKeys("\b\b\b\b\b\b\b\b\b\b");
        form.$("[placeholder='Дата встречи']").setValue(futureDay).click();
        form.$$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification]").shouldBe(visible);
        form.$$("button").find(exactText("Перепланировать")).click();
    }
}

