import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeExample {

  public static final String CLOUD_GOOGLE_COM_VISION = "https://cloud.google.com/vision/";

  public static void main(String[] args) throws IOException {

    if (args.length < 1) {
      System.out.println("Please enter the Image folder path to Analysis");
      return;
    }
    WebDriver driver;
    String imageFolderPath = args[0];
    System.setProperty("webdriver.chrome.driver", ConfigurationConstant.getChromeDrivePath());
    System.out.println("Launching chrome browser...");
    driver = new ChromeDriver();

    List<File> imageFileCollection = (List<File>) FileUtils.listFiles(new File(imageFolderPath), ConfigurationConstant.getImageExtensionList(), true);
    for (File file : imageFileCollection) {
      System.out.println("Analysis Image file: " + file.getName());
      String baseFileName = FilenameUtils.getBaseName(file.getCanonicalPath());
      String extractedTextFile = imageFolderPath + File.separator + "extractedText" + File.separator + baseFileName + ".doc";

      driver.navigate().to(CLOUD_GOOGLE_COM_VISION);
      driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"vision_demo_section\"]/iframe")));
      WebElement dropZone = driver.findElement(By.id("input"));
      dropZone.sendKeys(imageFileCollection.get(0).getCanonicalPath());

      WebDriverWait wait = new WebDriverWait(driver, 60);
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"textAnnotations\"]/div")));

      driver.findElement(By.xpath("//*[@id=\"textAnnotations\"]/div")).click();
      String extractedText = driver.findElement(By.xpath("//*[@id=\"card\"]/div[2]/div[1]/div")).getText();

      FileUtils.write(new File(extractedTextFile), extractedText, false);
    }
    System.out.println("Total Analysed Image files Count : " + imageFileCollection.size());
  }
}
