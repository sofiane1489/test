package us.piit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
public class LoginTest extends SetUp {
    Logger log= LogManager.getLogger(LoginTest.class.getName());


       @Test
       public void validCred(){
        String actualTitle=getCurrentTtile();
        String expecteTitle="Swag Labs";
        Assert.assertEquals(expecteTitle,actualTitle);
           log.info(" landed on Swag Labs");
        //enter username,password,and click on login button
         type("#user-name","standard_user");
               log.info("username entered success");
         type("#password","secret_sauce");
               log.info("Password entered success");
        clickOn("#login-button");
               log.info("click on login button success");

            //check user if logged in
            String expectedHomePageHeader="Products";
            String actualHomePageHeader = getElementText("//span[text()='Products']");
            //String actualresult= getElementText("//span[text()='Products']");
            Assert.assertEquals(expectedHomePageHeader,actualHomePageHeader);
            log.info("Home page displayed success");
        }
     @Test
    public void invalidUsername(){
         String expecteTitle="Swag Labs";
         String actualTitle=getCurrentTtile();
         Assert.assertEquals(expecteTitle,actualTitle);
         log.info(" landed on Swag Labs");

        //enter username,password,and click on login button
         type("#user-name","invalid_username");
              log.info("username entered success");
         type("#password","secret_sauce");
              log.info("Password entered success");
        clickOn("#login-button");
             log.info("click on login button success");

        //Error validation
        String expectedError="Epic sadface: Username and password do not match any user in this service";
        String actualError=getElementText("//div[@class='error-message-container error']/h3");
        Assert.assertEquals(expectedError,actualError);
        log.info("validate error success");
    }
    @Test
    public void missingUsername(){
        String actualTitle=getCurrentTtile();
        String expecteTitle="Swag Labs";
        Assert.assertEquals(expecteTitle,actualTitle);
        log.info(" landed on Swag Labs");

        //enter username,password,and click on login button
        type("#user-name","");
             log.info("username entered success");
        type("#password","secret_sauce");
            log.info("Password entered success");
         clickOn("#login-button");
           log.info("click on login button success");

        //Error validation
        String expectedError="Epic sadface: sername is required";
        String actualError=getElementText("//*[@id=\"login_button_container\"]/div/form/div[3]");
        Assert.assertEquals(expectedError,actualError);
        log.info("validate error success");
    }

    @Test
    public void missingPassword(){
        String actualTitle=getCurrentTtile();
        String expecteTitle="Swag Labs";
        Assert.assertEquals(expecteTitle,actualTitle);
             log.info(" landed on Swag Labs");
        //enter username,password,and click on login button
        type("#user-name","standard_user");
               log.info("username entered success");
        type("#password","");
               log.info("Password entered success");
        clickOn("#login-button");
               log.info("click on login button success");
        //Error validation
        String expectedError="Epic sadface: Password is required";
        String actualError=getElementText("//*[@id=\"login_button_container\"]/div/form/div[3]");
        Assert.assertEquals(expectedError,actualError);
                log.info("validate error success");
    }

}



