package taskmanager.Objects.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Settings {
    private Properties prop = new Properties();
    private File file;
    private String filename = "Settings.properties";

    public Settings() {
        file = new File(filename);
        try (FileInputStream fis = new FileInputStream(file)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public URL getBGFile() {
        String mainbg = prop.getProperty("bgfile", "/img/MainBG.jpg");
        return (getClass().getResource(mainbg));
    }

    public Integer getMainWidth() {
        String mainwidth = prop.getProperty("panelwidth", "550");
        return Integer.parseInt(mainwidth);
    }

    public Integer getMainHeight() {
        String mainheight = prop.getProperty("panelheight", "768");
        return Integer.parseInt(mainheight);
    }

}
