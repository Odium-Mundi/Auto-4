package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

   LocalDate date = LocalDate.now();

    @Test
    void shouldReservationCardWhenValidMinData() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(3).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("Паршикова Виолетта");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldReservationCardWhenValidMoreData() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(100500).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("Паршикова Виолетта");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldReservationCardWhenInvalidLess3DaysData() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("Паршикова Виолетта");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Заказ на выбранную дату невозможен")).waitUntil(Condition.visible, 2000);
    }

    @Test
    void shouldReservationCardWhenInvalidCityOnEnglish() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Krasnodar");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("Паршикова Виолетта");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 2000);
    }


    @Test
    void shouldReservationCardWhenInvalidCity() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодыр");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("Паршикова Виолетта");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).waitUntil(Condition.visible, 2000);
    }

    @Test
    void shouldReservationCardWhenInvalidNameOnEnglish() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("Parshikova Vi");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно")).waitUntil(Condition.visible, 2000);
    }

    @Test
    void shouldReservationCardWhenInvalidNameWithSymbols() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("Паришкова %Хах");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно")).waitUntil(Condition.visible, 2000);
    }


    @Test
    void shouldReservationCardWhenValidNameWithHyphen() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("К-катя К-катя");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldReservationCardWhenInvalidNumberPhone() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("К-катя К-катя");
        $("[name='phone']").setValue("+7920");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Телефон указан неверно")).waitUntil(Condition.visible, 1500);
    }

    @Test
    void shouldReservationCardWhenInvalidNumberPhoneWith8() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("К-катя К-катя");
        $("[name='phone']").setValue("89200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Телефон указан неверно")).waitUntil(Condition.visible, 1500);
    }

    @Test
    void shouldReservationCardWhenInvalidNumberPhoneWithSymbol() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("К-катя К-катя");
        $("[name='phone']").setValue("+7920000000%");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Телефон указан неверно")).waitUntil(Condition.visible, 1500);
    }

    @Test
    void shouldReservationCardWhenInvalidNumberPhoneWith12Numeral() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("К-катя К-катя");
        $("[name='phone']").setValue("+792000000002");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Телефон указан неверно")).waitUntil(Condition.visible, 1500);
    }

    @Test
    void shouldReservationCardWhenInvalidCheckbox() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue(date.plusDays(4).format(DateTimeFormatter.ofPattern(("dd.MM.yyyy"))));
        $("[data-test-id='name'] .input__control").setValue("К-катя К-катя");
        $("[name='phone']").setValue("+79200000000");
        $(byText("Забронировать")).click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(text("Я соглашаюсь с условиями"));
    }
}
