package taskmanager.Objects;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import taskmanager.Objects.tasks.Task;
import taskmanager.Objects.tasks.Tasks;
import taskmanager.swing.TMPanel;
import taskmanager.swing.page.CenterMenu;
import taskmanager.swing.page.center.TasksPart;

public class Methods {

    public static void displayMenu(CenterMenu cm, TMPanel lm, Map.Entry<Integer, Task> entry) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat dayFormat = new SimpleDateFormat("EEEE");
        JTextArea field_text = new JTextArea(5, 25);
        List<JTextField> field_tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            field_tasks.add(new JTextField());
        }
        JTextField field_label = new JTextField();
        JTextField field_date;
        JLabel day_of_week;
        String ru_action;
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("");
        Tasks tasks = new Tasks();
        List<String> themes = tasks.getThemes();
        for (String theme : themes) {
            comboBox.addItem(theme);
        }
        comboBox.addActionListener((ae) -> {
            field_label.setText(comboBox.getItemAt(comboBox.getSelectedIndex()));
        });
        JSlider jSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 10, 0);
        jSlider.setMajorTickSpacing(1);
        jSlider.setMinorTickSpacing(1);
        jSlider.setSnapToTicks(true);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        if (entry == null) {
            ru_action = "Добавить задание";
            field_date = new JTextField(df.format(new Date()));
            day_of_week = new JLabel(dayFormat.format(new Date()));

        } else {
            ru_action = "Редактировать задание";
            field_date = new JTextField(df.format(entry.getValue().getDateNeedTo()));
            day_of_week = new JLabel(dayFormat.format(entry.getValue().getDateNeedTo()));
            field_label.setText(entry.getValue().getTheme());
            String text = entry.getValue().getDescription();
            String[] DescAndTasks = text.split("\\|\\|\\|");
            if (DescAndTasks.length == 2) {
                text = DescAndTasks[0];
                String[] sTasks = DescAndTasks[1].split(";");

                for (int i = 0; i < sTasks.length; i++) {
                    JTextField ttf = new JTextField(sTasks[i]);
                 if (sTasks[i].endsWith("</s>"))
                     ttf.setEnabled(false);
                    field_tasks.set(i, ttf);
                }
            }
            field_text.setText(text);
            jSlider.setValue(entry.getValue().getSort());
        }

        field_text.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(field_text);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel panel = new JPanel(new GridBagLayout());
        field_label.setColumns(25);
        JButton add_day = new JButton("+");

        add_day.addActionListener(al -> {

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            try {

                Date date = formatter.parse(field_date.getText());
                Calendar cl = Calendar.getInstance();
                cl.setTime(date);
                cl.add(Calendar.DATE, 1);
                field_date.setText(formatter.format(cl.getTime()));
                day_of_week.setText(dayFormat.format(cl.getTime()));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        add_day.setToolTipText("+1 день");

        JPanel datePart = new JPanel();
        datePart.add(field_date);
        datePart.add(add_day);
        datePart.add(day_of_week);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        panel.add(new JLabel("Дата решения:"), c);
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        panel.add(datePart, c);

        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Тема:"), c);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(field_label, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel(""), c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridy = 2;
        panel.add(comboBox, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Приоритет:"), c);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(jSlider, c);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 4;
        panel.add(new JSeparator(), c);
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(new JLabel("Описание:"), c);
        c.gridx = 1;
        c.gridy = 5;
        panel.add(scroll, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 6;
        panel.add(new JLabel("Задания:"), c);
        c.gridy = GridBagConstraints.RELATIVE;
        for (int i = 0; i < 10; i++) {
            c.insets = new Insets(0, 0, 0, 0);
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;

            field_tasks.get(i).setColumns(33);
            JPanel jp = new JPanel();

            JButton done = new JButton("✓");
            done.setActionCommand("" + i);
            done.setMargin(new Insets(0, 0, 0, 0));
            done.addActionListener(al -> {
                if (field_tasks.get(Integer.parseInt(al.getActionCommand())).isEnabled()) {
                    field_tasks.get(Integer.parseInt(al.getActionCommand())).setEnabled(false);
                    StringBuilder generateBrackets = new StringBuilder();
                    generateBrackets.append("<s>");
                    generateBrackets.append(field_tasks.get(Integer.parseInt(al.getActionCommand())).getText());
                    generateBrackets.append("</s>");
                    field_tasks.get(Integer.parseInt(al.getActionCommand())).setText(generateBrackets.toString());
                } else {
                    field_tasks.get(Integer.parseInt(al.getActionCommand())).setEnabled(true);
                    String tempText = field_tasks.get(Integer.parseInt(al.getActionCommand())).getText();
                    tempText = tempText.replaceAll("(</s>)|(<s>)", "");
                    field_tasks.get(Integer.parseInt(al.getActionCommand())).setText(tempText);
                }

            });

            done.setBorder(BorderFactory.createCompoundBorder());
            jp.add(field_tasks.get(i));
            jp.add(done);

            panel.add(jp, c);

        }
        int result = JOptionPane.showConfirmDialog(null, panel, ru_action,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {

                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date date = formatter.parse(field_date.getText());
                StringBuilder extra = new StringBuilder();
                for (JTextField field_task : field_tasks) {

                    extra.append(field_task.getText() + ";");
                }
                String text = field_text.getText() + "|||" + extra;
                Task task2 = new Task(true, false, jSlider.getValue(), new Date(), date, date, field_label.getText(), text);
                if (entry != null) {
                    tasks.updateTask(entry.getKey(), task2);
                } else {
                    tasks.addTask(task2);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            c.gridwidth = 2;
            c.gridy = 2;

            cm.remove(cm.getComponent(2));
            cm.add(new TasksPart(cm, lm), c);
            lm.repaint();

        }
    }

}
