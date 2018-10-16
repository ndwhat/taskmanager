package taskmanager.swing.page.center;

import taskmanager.Objects.properties.Fonts;
import taskmanager.swing.TMPanel;
import taskmanager.swing.page.CenterMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

public class DatePart extends JComponent {

    static int padding = 10;
    static int timer = 555;

    @Override
    protected void paintComponent(Graphics g) {
    }

    public DatePart(TMPanel lm) {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Date date = new Date();
        String formatDMY = new SimpleDateFormat("dd MMMM YYYY", Locale.getDefault()).format(date);
        JLabel dmy = new JLabel(formatDMY + "г.");
        dmy.setBorder(new EmptyBorder(0, padding, 0, 0));
        dmy.setFont(Fonts.H2.getFont());
        dmy.setForeground(Color.WHITE);
        String formatDay = new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
        JLabel day = new JLabel(formatDay);
        day.setBorder(new EmptyBorder(0, padding, 0, 0));
        day.setFont(Fonts.H2.getFont());
        day.setForeground(Color.LIGHT_GRAY);
        String his = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(date);

        JLabel time = new JLabel(his);
        time.setBorder(new EmptyBorder(0, padding, 0, 0));
        Timer orangeTimer = new Timer(timer, e -> {
            Date dateNew = new Date();
            time.setText(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(dateNew));

            lm.repaint(0, 0, 1, 1);
        });

        time.setFont(Fonts.H1.getFont());
        time.setForeground(Color.YELLOW);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        add(dmy, c);

        c.gridwidth = 1;
        c.gridx = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        add(day, c);

        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 2;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_END;
        add(time, c);

        orangeTimer.start();

    }

}
