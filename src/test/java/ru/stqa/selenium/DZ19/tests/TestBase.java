package ru.stqa.selenium.DZ19.tests;

import org.junit.Before;
import ru.stqa.selenium.DZ19.app.Application;

public class TestBase {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

//    public EventFiringWebDriver driver;
//    public WebDriverWait wait;
//    public int waitTime = 7;
//    public BrowserMobProxy proxy;

//    public static class MyListener extends AbstractWebDriverEventListener {
//        @Override
//        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//            System.out.println(by);
//        }
//
//        @Override
//        public void afterFindBy(By by, WebElement element, WebDriver driver) {
//            System.out.println(by + " found");
//        }
//
//        @Override
//        public void onException(Throwable throwable, WebDriver driver) {
//            System.out.println(throwable);
//            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File screen = new File("screen-" + System.currentTimeMillis() + ".png");
//            try {
//                Files.copy(tmp, screen);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println(screen);
//        }
//    }

    @Before
    public void start() {
        if (tlApp.get() !=null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));

//        //это что то связанное с многопоточностью взял из видео selenium3_l10_m7_proxy
//        if (tlDriver.get() != null) {
//            driver = tlDriver.get();
//            wait = new WebDriverWait(driver, 10);
//            return;
//        }


//        proxy = new BrowserMobProxyServer();
//        proxy.start(0);
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);// get the Selenium proxy object
//        DesiredCapabilities capabilities = new DesiredCapabilities();// configure it as a desired capability






//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("localhost:8888");
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("proxy", proxy);
//        WebDriver driver = new ChromeDriver(caps);
//        FirefoxOptions options = new FirefoxOptions()
//                .addPreference("network.proxy.allow_hijacking_localhost", true)
//                .setProxy(proxy);
//
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
//
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("w3c", false);
//
//        //DesiredCapabilities cap = DesiredCapabilities.chrome();
//
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//
//        //cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//        //options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//
//        driver = new EventFiringWebDriver(new ChromeDriver(options));
//        driver.register(new MyListener());
//        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver, waitTime);

//        //это что то связанное с многопоточностью взял из видео selenium3_l10_m7_proxy
//        Runtime.getRuntime().addShutdownHook(
//                new Thread(() -> { driver.quit(); driver = null; }));
    }



//    @After
//    public void stop() {
//        driver.quit();
//        driver = null;
//    }



}
