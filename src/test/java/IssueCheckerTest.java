import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueCheckerTest extends TestBase{

    private final String repository = "/eroshenkoam/allure-example";
    private final String issueSelector = "#issues-tab";
    private final String issueNumber = "90";


    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("kmigor")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://github.com/kmigor")
    @DisplayName("Проверка issue под номером #90 на чистом Selenide (с Listener)")
    public void issueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(repository);

        $(issueSelector).click();
        $(withText(issueNumber)).should(Condition.exist);
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("kmigor")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "Testing", url = "https://github.com/kmigor")
    @DisplayName("Проверка issue под номером #90 при помощи лямбд через step")
    public void lambdaIssueSearchTest(){
        step("Открываем искомый репозиторий", () -> {
            open(repository);
        });
        step("Открываем таб Issues", () -> {
            $(issueSelector).click();
        });
        step("Проверяем наличие Issue с номером " + issueNumber, () -> {
            $(withText("#" + issueNumber)).should(Condition.exist);
        });
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("kmigor")
    @Severity(SeverityLevel.TRIVIAL)
    @Link(value = "Testing", url = "https://github.com/kmigor")
    @DisplayName("Проверка issue под номером #90 при помощи @Step")
    public void stepsIssueSearchTest(){
        WebSteps steps = new WebSteps();
        steps.openMainPage(repository);
        steps.openIssuesTab(issueSelector);
        steps.shouldSeeIssueWithNumber(issueNumber);
    }
}