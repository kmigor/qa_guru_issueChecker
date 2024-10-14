import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем искомый репозиторий")
    public void openMainPage(String repository) {
        open(repository);
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab(String issueSelector) {
        $(issueSelector).click();
    }

    @Step("Проверяем наличие Issue с номером {issueNumber}")
    public void shouldSeeIssueWithNumber(String issueNumber) {
        $(withText("#" + issueNumber)).should(Condition.exist);
    }
}