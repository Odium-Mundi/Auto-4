import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldReservationCardWhenValidData() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Краснодар");
        $("[placeholder='Дата встречи'").sendKeys(Keys.LEFT_SHIFT, Keys.HOME, Keys.DELETE);
        $("[placeholder='Дата встречи'").setValue("30092020");
        $("[data-test-id=\"name\"] .input__control").setValue("Паршикова Виолетта");
        $("[name='phone']").setValue("+79200000000");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
        // TODO:
    }
}
