package pl.peekquick.test.arquillian;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Copyright (C) Zalass Consulting, 2013-2018
 * <p>
 * Created by Tomek on 2018-01-29.
 */
public class IndexPage {

    @FindBy(tagName = "h1")
    private WebElement webElement;

    boolean isHeaderCorrect() {

        System.out.println("Checking if header web element contains what it should contain: " + webElement);

        return webElement.getText().contains("Hello, world!");
    }

}
