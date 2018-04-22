import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ConfigurationConstant {

  public static final String CHROMEDRIVER_EXE = "chromedriver.exe";
  public static final String DRIVER_PATH = "D:\\Tool\\ChromeDriver";

  static String getChromeDrivePath() {
    return DRIVER_PATH + File.separator+ CHROMEDRIVER_EXE;
  }

  static String[] getImageExtensionList(){
    return new String[] {"bmp", "gif", "ico", "jpeg", "png", "tiff", "webp"};
  }
}
