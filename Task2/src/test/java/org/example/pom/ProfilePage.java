package org.example.pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    public ProfilePage navigateToProfilePage() {
        $$("a.svelte-1rc85o5").findBy(Condition.text("Hello")).click();
        $$("li.mdc-deprecated-list-item").findBy(Condition.text("Profile"))
                .click();
        return this;
    }

    public ProfilePage openEditModal() {
        $$("button[title='More options'], button[title='Edit']").findBy(Condition.visible)
                .shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        return this;
    }

//    public ProfilePage changeBirthdate(String birthdate) {
//        SelenideElement dateField = $("input.mdc-text-field__input[type='date']");
//        dateField.click();
//
//        dateField.sendKeys(Keys.HOME);
//
//        for (int i = 0; i < 10; i++) {
//            dateField.sendKeys(Keys.DELETE);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            dateField.sendKeys(Keys.ARROW_LEFT);
//        }
//
//        dateField.setValue(birthdate);
//        return this;
//    }

//    public ProfilePage changeBirthdate(String birthdate) {
//        SelenideElement dateField = $("input.mdc-text-field__input[type='date']");
//
//        dateField.click();
//
//        dateField.sendKeys(Keys.HOME);
//
//        for (int i = 0; i < 10; i++) {
//            dateField.sendKeys(Keys.DELETE);
//        }
//
//        sleep(500);
//
//        dateField.sendKeys(birthdate);
//
//        dateField.sendKeys(Keys.TAB);
//
//        return this;
//    }

//    public ProfilePage changeBirthdate(String birthdate) {
//        SelenideElement dateField = $("input.mdc-text-field__input[type='date']");
//
//        dateField.click();
//        sleep(1000);
//
//        dateField.sendKeys(Keys.DELETE);
//        sleep(1000);
//
//        dateField.sendKeys(Keys.ARROW_LEFT);
//        sleep(1000);
//
//        dateField.sendKeys(Keys.ARROW_LEFT);
//        sleep(1000);
//
//        dateField.sendKeys(Keys.ARROW_LEFT);
//        sleep(1000);
//
//        for (char ch : birthdate.toCharArray()) {
//            dateField.sendKeys(String.valueOf(ch));
//            sleep(1000);
//        }
//        sleep(1000);
//
//        dateField.sendKeys(Keys.ENTER);
//        sleep(1000);
//
//        return this;
//    }

//    public ProfilePage changeBirthdate(String birthdate) {
//        SelenideElement dateField = $("input.mdc-text-field__input[type='date']");
//        sleep(1000);
//
//        // Форматируем дату в стандартный формат, который принимается полем ввода.
//        String formattedDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
//                .format(DateTimeFormatter.ISO_LOCAL_DATE);
//        sleep(1000);
//
//        // Устанавливаем значение напрямую через JavaScript
//        executeJavaScript("arguments[0].value = arguments[1];", dateField, formattedDate);
//        sleep(1000);
//
//        // Инициируем событие 'change' для поля ввода
//        executeJavaScript("arguments[0].dispatchEvent(new Event('change'));", dateField);
//        sleep(1000);
//
//        return this;
//    }

//    public ProfilePage changeBirthdate(String birthdate) {
//        SelenideElement dateField = $("input.mdc-text-field__input[type='date']");
//        String formattedDate = birthdate.replace(".", "/");
//
//        dateField.click();
//        dateField.clear();
//
//        // Используем JavaScript для установки значения поля
//        executeJavaScript("arguments[0].value = arguments[1];", dateField, formattedDate);
//
//        dateField.pressEnter();
//
//        return this;
//    }

    public ProfilePage changeBirthdate(String birthdate) {
        $("input.mdc-text-field__input[type='date']").setValue(birthdate);
        return this;
    }

    public ProfilePage setDatepicker(String cssSelector, String date) {
        executeJavaScript("jQuery(\"input.mdc-text-field__input[type='date']\").datepicker('setDate', '11.11.1111')");
        return this;
    }

    public ProfilePage saveChanges() {
        sleep(1000);
        $("button.button.mdc-button.mdc-button--raised").shouldBe(Condition.enabled).click();
        sleep(1000);
        return this;
    }


    public ProfilePage closeEditModal() {
        sleep(1000);
        $("button.mdc-dialog__close").shouldBe(Condition.enabled).click();
        sleep(1000);
        return this;
    }

    public String getDateOfBirth() {
        sleep(1000);
        return $$(".row.svelte-vyyzan").findBy(Condition.text("Date of birth"))
                .$$(".content.svelte-vyyzan").first().text();
    }
}