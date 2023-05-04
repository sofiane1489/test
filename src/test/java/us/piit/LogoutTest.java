package us.piit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends SetUp {

    //create logger to replace system.out.print("")
    Logger log= LogManager.getLogger(LogoutTest.class.getName());

      /*we are able to extend from another package with the key word extends
      because the actual package has the same Name as the package that we are extending from
      their package have the same name:us.piit,it's like the classes are in the same package,
      otherwise we would import the package and class name in order to be able to use the class properties
       and obliviously by creating an object of that class*/

    @Test
    public void logout() throws InterruptedException {
        //enter username,password,and click on login button
        type("#user-name","standard_user");
                log.info("username entered success");
        type("#password","secret_sauce");
               log.info("Password entered success");
        clickOn("#login-button");
               log.info("click on login button success");

        //check user if logged in
        String actualresult= getElementText("//*[@id=\"header_container\"]/div[2]/span");
        String expecteresult="Products";
        Assert.assertEquals(expecteresult,actualresult);
               log.info("User logged in success");

        //click on humberger menu
        clickOn("#react-burger-menu-btn");
                log.info("click on humberger menu success");
        //click on logout link
        clickOn("#logout_sidebar_link");
                log.info("click on logout lik success");

       //check if user landed in the login page(logged out)
       boolean loginPageHeaderIsDisplayed= isVisible("#root > div > div.login_logo");
       Assert.assertTrue(loginPageHeaderIsDisplayed);
               log.info("login page header is displayed success");
       //validate the login page header text
       String expectedloginPageHeaderText="Swag Labs";
       String actualloginPageHeaderText=getElementText("#root > div > div.login_logo");
       Assert.assertEquals(expectedloginPageHeaderText,actualloginPageHeaderText);
                    log.info("login page header text validation,logged out success");


    }
}
