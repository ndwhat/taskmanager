package taskmanager.swing;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import taskmanager.Objects.properties.Settings;

/**
 */
public class TMMEnuBar extends JMenuBar {

    Settings settings = new Settings();

    public TMMEnuBar(TMPanel panel) {
        JMenu mFile = new JMenu("File");
        JMenuItem addTask = new JMenuItem("Add task");
        JMenuItem iExit = new JMenuItem("Exit");

        JMenu iSettings = new JMenu("Settings");

        iExit.addActionListener(ae -> System.exit(0));

        mFile.add(addTask);
        mFile.addSeparator();
        mFile.add(iExit);

        add(mFile);
        add(iSettings);
    }

}
