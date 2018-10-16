package taskmanager.swing;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import taskmanager.Objects.properties.Settings;
import taskmanager.swing.page.*;
import taskmanager.swing.page.CenterMenu;
public class TMPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Settings settings = new Settings();
        Image img;
        try {
            img = ImageIO.read(settings.getBGFile());
            g2d.drawImage(img, 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(CenterMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TMPanel() {
          
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
       
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 10;
        c.ipady = 10;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        add(new CenterMenu(this),c);
    
    }

}
