import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void practiceForm() {
        open("/automation-practice-form");

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("aivanova@test.com");
        $("#userNumber").setValue("8934567890");

        $(byText("Female")).click();

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--001").click();

        $("#subjectsInput").setValue("English").pressEnter();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/ozero.jpg"));
        $("#currentAddress").setValue("address");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Anna Ivanova"),
                text("aivanova@test.com"),
                text("Female"),
                text("8934567890"),
                text("01 July,1990"),
                text("English"),
                text("Music"),
                text("ozero.jpg"),
                text("address"),
                text("Haryana Karnal")
        );
    }
}
