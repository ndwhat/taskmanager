package taskmanager.swing.page;

import taskmanager.swing.TMPanel;
import taskmanager.swing.page.center.DatePart;
import taskmanager.swing.page.center.TasksPart;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class CenterMenu extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
    }

    public CenterMenu(TMPanel panel) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        add(new DatePart(panel), c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        add(new TopMenu(this, panel), c);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        add(new TasksPart(this, panel), c);
    }

}
