import com.sun.source.tree.UsesTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.StringConcatFactory;

public class GoryankaTest {

    //    TC_1_1  - Тест кейс:
    //    //1. Открыть страницу https://openweathermap.org/
    //    //2. Набрать в строке поиска город Paris
    //    //3. Нажать пункт меню Search
    //    //4. Из выпадающего списка выбрать Paris, FR
    //    //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\All4u\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFranceChoiceInDropDownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text()= 'Paris, FR ']")
        );
        parisFranceChoiceInDropDownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actialResult = h2CityCountryHeader.getText();

        //Thread.sleep(5000);

        //assert
        Assert.assertEquals(actialResult, expectedResult);

        //Thread.sleep(5000);

        driver.quit();
        // driver.close();
    }
//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со
//    ссылкой https://openweathermap.org/guide и что title
//    этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void test_guideMenuAndTitle() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\All4u\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String buttomGuide = "Guide";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement guideButton = driver.findElement(
                By.xpath("//a[@href = '/guide']")
        );
        guideButton.click();

        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        //assert
        Assert.assertEquals(actualResultUrl, expectedResult1);
        Assert.assertEquals(actualResultTitle, actualResultTitle);

        driver.quit();

    }

    /*TC_11_02
    1.  Открыть базовую ссылку
    2.  Нажать на единицы измерения Imperial: °F, mph
    3.  Подтвердить, что температура для города показана в Фарингейтах
    */
    @Test
    public void testTempInFahrenheit() throws InterruptedException {
        //arrange
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\All4u\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String tempFSymbol = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement tempFahrenheitButton = driver.findElement(
                By.xpath("//div[@class = 'switch-container']/div[@class = 'option']/following-sibling::div")
        );
        tempFahrenheitButton.click();

        WebElement currentTempF = driver.findElement(
                By.xpath("//div[@class = 'current-temp']/span")
        );
        Boolean isTempInFahrenheit = currentTempF.getText().contains("°F");


        String tempInFahrenheit = currentTempF.getText();

        String actualResult = tempInFahrenheit.substring(tempInFahrenheit.length() - 2);

        Assert.assertTrue(currentTempF.getText().contains(tempInFahrenheit));
        Assert.assertEquals(actualResult, expectedResult);

        Thread.sleep(3000);

        driver.quit();

    }

    /*TC_11_03
    1. Открыть базовую ссылку
    2. Подтвердить, что внизу страницы есть панель с текстом
       “We use cookies which are essential for the site to work.
        We also use non-essential cookies to help us improve our services.
        Any data collected is anonymised. You can allow all cookies or manage
        them individually.”
    3. Подтвердить, что на панели внизу страницы есть 2 кнопки
       “Allow all” и “Manage cookies”
    */
    @Test
    public void testToProveTwoButtonsInThePanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\All4u\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        driver.get(url);
        Thread.sleep(5000);


        WebElement textElement = driver.findElement(
                By.className("stick-footer-panel_description")
        );

        String actualResult = textElement.getText();
        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[text()='Allow all']")
        );

        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']")
        );


        //String actualResult = textElement.getText();

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(buttonAllowAll.getText(), "Allow all");
        Assert.assertEquals(buttonManageCookies.getText(), "Manage cookies");


        driver.quit();

    }

    @Test
 /*
    TC_11_04
    1.  Открыть базовую ссылку
    2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
 */
    public void testSupprtButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\All4u\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        driver.get(url);

        driver.manage().window().maximize();
        Thread.sleep(5000);

        String expectedFaqResult = "FAQ";
        String expectedStartButtonResult = "How to start";
        String expectedAskQuestionButtonResult = "Ask a question";

//    Assert.assertEquals(
//   driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']/*\""))
//   .getSize(), 3
//            );

        WebElement supportDropDown = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportDropDown.click();
        Thread.sleep(5000);


        WebElement checkIfFaqButtonPresent = driver.findElement(
                By.xpath("//*[@id=\"support-dropdown-menu\"]/li[1]/a")
        );

        String actualResultIfFaqPresent = checkIfFaqButtonPresent.getText();
        Assert.assertEquals(actualResultIfFaqPresent, expectedFaqResult);

        WebElement checkIfHowToStartButtonPresent = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href = '/appid']")
        );

        String actualStartButtonResult = checkIfHowToStartButtonPresent.getText();
        Assert.assertEquals(actualStartButtonResult, expectedStartButtonResult);

        WebElement checkAskQuestionButtonPresent = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']//a[@href = 'https://home.openweathermap.org/questions']")
        );

        String actualAskQuestionButtonResult = checkAskQuestionButtonPresent.getText();
        Assert.assertEquals(actualAskQuestionButtonResult, expectedAskQuestionButtonResult);


    }

    @Test
    public void testThreeSubmenusAreDisplaye_whenClickingSupportMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\All4u\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String expectedSubMenu1 = "FAQ";
        String expectedSubMenu2 = "How to start";
        String expectedSubMenu3 = "Ask a question";

        String baseUrl = "https://openweathermap.org/";
        driver.get(baseUrl);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportDropDown = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportDropDown.click();
        Thread.sleep(5000);


        //Assert.assertEquals(driver.findElements(By.xpath("//div[@id = 'support-dropdown']/ul[@id = 'support-dropdown-menu']/li  ")).size(),3);

        String actualSubMenu1 = driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']/li[1]")).getText();
        String actualSubMenu2 = driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']/li[2]")).getText();
        String actualSubMenu3 = driver.findElement(By.xpath("//ul[@id = 'support-dropdown-menu']/li[3]")).getText();

        Assert.assertEquals(actualSubMenu1, expectedSubMenu1);
        Assert.assertEquals(actualSubMenu2, expectedSubMenu2);
        Assert.assertEquals(actualSubMenu3, expectedSubMenu3);


        driver.quit();


    }

    /*TC_11_05
        1. Открыть базовую ссылку
        2. Нажать пункт меню Support → Ask a question
        3. Заполнить поля Email, Subject, Message
        4. Не подтвердив CAPTCHA, нажать кнопку Submit
        5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */

    @Test
    public void captcahError() throws InterruptedException {
        //Initiate ChromrDriver browser
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\All4u\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "tester@test.com";
        String subject = "other";
        String message = "blahblahblah";
        String expectedReCaptchaErrorMessage = "reCAPTCHA verification failed, please try again.";

        //maximize the browser
        driver.manage().window().maximize();
        //pass aplication url
        driver.get(url);

        Thread.sleep(3000);

        WebElement supportMenuButton = driver.findElement(
                By.xpath("//li[@class = 'with-dropdown']")
        );
        Thread.sleep(5000);

        supportMenuButton.click();

        WebElement askQuestionButton = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']//a[@href = 'https://home.openweathermap.org/questions']")

        );
        Thread.sleep(5000);
        askQuestionButton.click();

        //SWITCHING TO THE NEW WINDOW

        String mainWindow = driver.getWindowHandle();
        for (String windowsHandle : driver.getWindowHandles()) {
            if (!mainWindow.contains(windowsHandle)) {
                driver.switchTo().window(windowsHandle);
                break;
            }

        }


        WebElement emailField = driver.findElement(
                By.xpath("// div[@class = 'col-sm-8']//input[@id = 'question_form_is_user_true']")
        );
        Thread.sleep(2000);
        emailField.click();
        emailField.sendKeys(email);

        WebElement selectField = driver.findElement(
                By.xpath("//*[@id=\"question_form_email\"]")
        );
        selectField.click();
        Thread.sleep(3000);

        WebElement selectFieldChoice = driver.findElement(
                By.xpath("//option[@value = 'Sales']")
        );
        selectFieldChoice.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class = 'form-control text required']")
        );
        Thread.sleep(2000);
        messageField.click();
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(
                By.xpath("//div[@class ='col-sm-8']//input[@type ='submit']")
        );
        Thread.sleep(2000);
        submitButton.click();


        WebElement reCaptchaErrorMessage = driver.findElement(
                By.xpath("//div[@class='help-block']")
        );
        String actualResult = reCaptchaErrorMessage.getText();
        Assert.assertEquals(actualResult, expectedReCaptchaErrorMessage);

        driver.quit();
    }
}