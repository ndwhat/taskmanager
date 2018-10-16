package taskmanager.swing.page;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import taskmanager.Objects.Methods;

import taskmanager.Objects.properties.Icons;
import taskmanager.Objects.tasks.Task;
import taskmanager.Objects.tasks.Tasks;
import taskmanager.swing.TMButton;
import taskmanager.swing.TMPanel;
import taskmanager.swing.page.center.TasksPart;

public class TopMenu extends JPanel {

  
    public TopMenu(CenterMenu cm, TMPanel lm) {

        setBackground(new Color(1, 1, 1, 0.2f));

        TMButton addTask = new TMButton("Добавить задание", 35, 35, Icons.ADDTASK.getFile());

        addTask.addActionListener(al -> {
            Methods.displayMenu(cm, lm, null);
        });
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;

        add(addTask, c);

    }

}
