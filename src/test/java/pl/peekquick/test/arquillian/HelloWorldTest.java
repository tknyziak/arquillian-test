package pl.peekquick.test.arquillian;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.api.Features;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;

/**
 * Copyright (C) Zalass Consulting, 2013-2018
 * <p>
 * Created by Tomek on 2018-01-26.
 */
@RunWith(ArquillianCucumber.class)
@Features("classpath:features")
public class HelloWorldTest {

    @Deployment(testable = false)
    public static Archive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(MavenImporter.class)
                .loadPomFromFile("pom.xml")
                .importBuildOutput()
                .as(WebArchive.class);

        webArchive.writeTo(System.out, Formatters.VERBOSE);

        return webArchive;
    }

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @When("^I hit the welcome page$")
    public void testSetup() {
        Assert.assertNotNull(browser);
        Assert.assertNotNull(deploymentUrl);
        browser.get(deploymentUrl.toString());
    }

    @Then("^I should see a <([^\"]+)> element with \"([^\"]+)\" inside$")
    public void isHeaderCorrect(String tagName, String contents) {
        WebElement element = browser.findElement(By.tagName(tagName));
        Assert.assertNotNull(element);
        Assert.assertTrue(element.getText().contains(contents));
    }

    public static void main(String[] args) {
        createDeployment();
    }

}
