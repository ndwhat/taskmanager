package taskmanager.swing.page.center;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;

import taskmanager.Objects.Methods;

import taskmanager.Objects.properties.Fonts;
import taskmanager.Objects.properties.Icons;
import taskmanager.Objects.properties.Settings;
import taskmanager.Objects.tasks.Task;
import taskmanager.Objects.tasks.Tasks;
import taskmanager.swing.TMButton;
import taskmanager.swing.TMPanel;
import taskmanager.swing.page.CenterMenu;

import static taskmanager.swing.page.center.DatePart.padding;

public class TasksPart extends JComponent {

    public TasksPart(CenterMenu cm, TMPanel panel) {

        setLayout(new GridBagLayout());
        Tasks tasks = new Tasks();
        Map<Integer, Task> map = tasks.getMap();

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jPanel.setBackground(null);
        jPanel.setBorder(null);
        jPanel.setOpaque(false);
        for (Map.Entry<Integer, Task> entry : map.entrySet()) {
            JPanel table = new JPanel();
            JPanel rightPart = new JPanel();

            rightPart.setBackground(new Color(1, 1, 1, 0.0f));
            JLabel label = new JLabel();
            label.setBorder(new EmptyBorder(10, 10, 0, 0));
            label.setForeground(Color.LIGHT_GRAY);
            label.setFont(Fonts.H3.getFont());
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            String labelText = entry.getValue().getTheme();

            if (labelText.length() > 28) {
                label.setText(entry.getValue().getTheme().substring(0, 28) + "..");
            } else {
                label.setText(entry.getValue().getTheme());
            }
            int howmany = 0;
            int finished = 0;
            JLabel dmy = new JLabel();
            dmy.setForeground(Color.YELLOW);
            dmy.setFont(Fonts.SMALL.getFont());
            Date dateTo = entry.getValue().getDateNeedTo();
            String formatDMY = new SimpleDateFormat("dd MMMM", Locale.getDefault()).format(dateTo);
            String formatDAY = new SimpleDateFormat("EEEE", Locale.getDefault()).format(dateTo);
            JLabel percent = new JLabel();
            dmy.setText("<html><div style='text-align:right'><small><span style='color:white'>" + formatDAY + "</span></small><br>" + formatDMY + "</div></html>");
            percent.setFont(Fonts.P.getFont());
            percent.setForeground(Color.WHITE);
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String f_cur_date = formatter.format(new Date());
            try {
                Date cl = formatter.parse(f_cur_date);
                if (dateTo.compareTo(cl) < 0) {
                    table.setBackground(new Color(1.0f, 0.1f, 0.2f, 0.2f));
                } else if (cl.compareTo(dateTo) == 0) {
                    table.setBackground(new Color(1.0f, 1.0f, 0.1f, 0.2f));
                } else {
                    table.setBackground(new Color(0.1f, 1.0f, 0.2f, 0.2f));
                }
            } catch (ParseException ex) {
                Logger.getLogger(TasksPart.class.getName()).log(Level.SEVERE, null, ex);
            }
            JLabel desc = new JLabel();
            desc.setBorder(new EmptyBorder(0, 10, 0, 0));
            desc.setForeground(Color.WHITE);
            desc.setFont(Fonts.SMALL.getFont());
            String descText = entry.getValue().getDescription();
            descText = descText.replace("\n", "<br>");
            String[] DescAndTasks = descText.split("\\|\\|\\|");
            ArrayList<String> tempArr = new ArrayList<>(Arrays.asList(DescAndTasks));
            descText = DescAndTasks[0];
            StringBuilder extra = new StringBuilder();
            if (tempArr.size() == 1)
                tempArr.add("");
            else {
                String[] sTasks = tempArr.get(1).split(";");
                extra.append("<ul>");
                for (int i = 0; i < sTasks.length; i++) {
                    if (!sTasks[i].isEmpty()) {
                        howmany++;
                        if (sTasks[i].endsWith("</s>")) finished++;
                        if (sTasks[i].length()>50) {
                            extra.append("<li>" + sTasks[i].substring(0, 38) + "..</li>");
                        }
                        else {
                            extra.append("<li>" + sTasks[i] + "</li>");
                        }
                    }
                }
                extra.append("</ul>");
            }
            int perc = howmany == 0 ? 0 : (finished * 100) / howmany;
            percent.setText("" + perc + "%");

            if (descText.length() >= 100) {
                desc.setText("<html><div style='width:" + new Settings().getMainWidth() / 2.3 + "px'><small>" + descText.substring(0, 100) + "..</div><small>" + extra.toString() + "</small></html>");

            } else {
                desc.setText("<html><div style='width:" + new Settings().getMainWidth() / 2.3 + "px'>" + descText + "</div><br><small>" + extra.toString() + "</small></html>");
            }
            TMButton done = new TMButton("Сделано", 30, 30, Icons.SUCCESS.getFile());
            done.setPadding(4);
            done.setMargin(8);
            TMButton ndone = new TMButton("Удалить", 28, 28, Icons.NOTSUCCESS.getFile());

            done.addActionListener(ae -> {
                tasks.removeTask(entry.getKey());
                GridBagConstraints c = new GridBagConstraints();
                c.gridwidth = 2;
                c.gridy = 2;
                cm.remove(this);
                cm.add(new TasksPart(cm, panel), c);
                panel.repaint();
            });

            ndone.setPadding(4);
            ndone.setMargin(8);
            TMButton edit = new TMButton("Смотреть", 37, 37, Icons.EDIT.getFile());
            edit.setActionCommand("edit");
            edit.addActionListener(al -> {
                Methods.displayMenu(cm, panel, entry);
            });
            table.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            /**
             * TEXT PART
             */
            c.insets = new Insets(5, 5, 10, 5);
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 0;
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.NONE;
            table.add(label, c);

            c.gridwidth = 2;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 0;
            c.weighty = 0;
            c.anchor = GridBagConstraints.LINE_END;
            c.fill = GridBagConstraints.NONE;
            table.add(dmy, c);
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0;
            c.weighty = 0;
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.NONE;
            table.add(desc, c);

            c.gridwidth = 2;
            c.gridx = 2;
            c.gridy = 1;
            c.ipadx = 0;

            c.weightx = 0;
            c.weighty = 0;
            c.anchor = GridBagConstraints.LAST_LINE_END;

            table.add(edit, c);

            /**
             * RIGHT PART BLOCK
             */
            rightPart.setLayout(new GridBagLayout());

            c.gridwidth = 1;
            c.insets = new Insets(0, 5, 10, 0);
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 0;
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.NONE;
            rightPart.add(percent, c);

            c.gridwidth = 1;
            c.insets = new Insets(0, 5, 8, 0);
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 0;
            c.anchor = GridBagConstraints.SOUTH;
            c.fill = GridBagConstraints.NONE;
            /*
             * IS DONE
             */
            rightPart.add(done, c);
            /**
             * GLOBAL BLOCK
             */

            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = GridBagConstraints.RELATIVE;
            c.weightx = 0;
            c.weighty = 0;
            jPanel.add(table, c);

            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = GridBagConstraints.RELATIVE;
            c.weightx = 0;
            c.weighty = 0;
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.NONE;
            jPanel.add(rightPart, c);
        }
        JScrollPane scroll = new JScrollPane(jPanel);
        scroll.getViewport().addChangeListener(e -> {
            panel.revalidate();
            panel.repaint();

        });
        scroll.setWheelScrollingEnabled(true);
        scroll.setBackground(null);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);

        scroll.getViewport().setIgnoreRepaint(true);
        scroll.setOpaque(false);

        scroll.setPreferredSize(new Dimension((int) (new Settings().getMainWidth() / 1.1), (int) (new Settings().getMainHeight() / 1.1)));
        scroll.setIgnoreRepaint(true);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll);
    }

}
