package org.example.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Instant;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.codeborne.selenide.Condition;
import io.qameta.allure.*;

@Epic("Тестирование веб-приложения")
@Feature("Управление профилем и студентами")
public class GeekBrainsStandTests extends BaseTest {

    @Test
    @Owner("Соминский Антон")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Авторизация в системе")
    @DisplayName("Тест входа без учетных данных")
    @Description("Проверка ошибки авторизации при попытке входа без заполнения полей")
    public void testLoginWithoutCredentialsShowsError() {
        loginPage.openPage().clickLoginButton();

        String errorMessage = $("div.error-block.svelte-uwkxn9 p").shouldBe(Condition.visible).text();
        assertTrue(errorMessage.contains("Invalid credentials."),
                "Ожидаемое сообщение об ошибке: 'Invalid credentials.', получено: '" + errorMessage + "'");
    }

    @Test
    @Owner("Соминский Антон")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Управление студентами")
    @DisplayName("Тест добавления и удаления студента")
    @Description("Проверка функциональности добавления нового студента и его удаления")
    public void testAddAndDeleteStudentFunctionality() {
        // Генерация уникального имени и логина для нового студента
        String uniqueName = "TestName" + Instant.now().toEpochMilli();
        String uniqueLogin = "TestLogin" + Instant.now().toEpochMilli();

        // Вход в систему и добавление студента
        loginPage.openPage().login(USERNAME, PASSWORD);
        mainPage.openAddStudentModal().fillStudentDetails(uniqueName, uniqueLogin).clickSaveButton();
        mainPage.closeAddStudentModal();

        // Проверка наличия студента в таблице
        assertTrue(mainPage.isStudentPresentInTable(uniqueName),
                "Студент с именем " + uniqueName + " не найден в таблице.");

        // Удаление студента и проверка его отсутствия в таблице
        mainPage.clickDeleteButtonForStudent(uniqueName).waitForDeletionToComplete(uniqueName);
    }

    @Test
    @Owner("Соминский Антон")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Редактирование профиля")
    @DisplayName("Тест изменения даты рождения")
    @Description("Проверка функционала изменения даты рождения в профиле пользователя")
    public void testBirthdateChangeFunctionality() {
        // Установка новой даты рождения
        String newBirthdate = "25.08.1989";

        // Вход в систему
        loginPage.openPage().login(USERNAME, PASSWORD);

        // Навигация на страницу профиля пользователя и открытие модального окна редактирования
        profilePage.navigateToProfilePage()
                .openEditModal()
                // Изменение даты рождения и сохранение изменений
                .changeBirthdate(newBirthdate)
                .saveChanges()
                // Закрытие модального окна
                .closeEditModal();

        // Получение и проверка установленной даты рождения
        String observedDate = profilePage.getDateOfBirth();
        assertEquals("25.08.1989", observedDate,
                "Ожидаемая дата рождения после изменения должна быть 25.08.1989, фактическая: " + observedDate);
    }
}