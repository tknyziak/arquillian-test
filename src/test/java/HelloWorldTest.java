import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * Copyright (C) Zalass Consulting, 2013-2018
 * <p>
 * Created by Tomek on 2018-01-26.
 */
@RunWith(Arquillian.class)
public class HelloWorldTest {

    @Deployment(testable = false)
    public static Archive createDeployment() {
        return ShrinkWrap.create(MavenImporter.class)
                .loadPomFromFile("pom.xml")
                .importBuildOutput()
                .as(WebArchive.class);
    }

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @Test
    public void testSetup() {
        Assert.assertNotNull(browser);
        Assert.assertNotNull(deploymentUrl);

        browser.get(deploymentUrl.toString());
        Assert.assertTrue(browser.getPageSource().contains("Hello, world!"));


    }

    public static void main(String[] args) {
        createDeployment();
    }

}
