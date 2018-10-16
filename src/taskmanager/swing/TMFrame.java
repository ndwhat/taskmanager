package taskmanager.swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import taskmanager.Objects.properties.Fonts;
import taskmanager.Objects.properties.Icons;
import taskmanager.Objects.properties.Settings;
import taskmanager.swing.page.CenterMenu;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class TMFrame extends JFrame {
    private int width = new Settings().getMainWidth();
    private int height = new Settings().getMainHeight();

    static TMPanel panel = new TMPanel();
    TMMainContainer MainContainer = new TMMainContainer();

    TMFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screenSize.width - width, 0,width ,screenSize.height-40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Task manager");
        add(MainContainer);

        try {
            setIconImage(ImageIO.read(Icons.CALLENDAR.getFile()));
        } catch (IOException ex) {
            Logger.getLogger(CenterMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(true);

        add(panel);

    }

    static public TMFrame execute() {

        return new TMFrame();
    }
}
