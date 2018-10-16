package taskmanager;

import java.util.Date;
import taskmanager.Objects.tasks.Task;
import taskmanager.Objects.tasks.Tasks;
import taskmanager.swing.TMFrame;

import javax.swing.*;

public class TaskManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(TMFrame::execute);
    }

    static void addTask() {
        Tasks tasks = new Tasks();
        Date date = new Date();
        Task task = new Task(true, false, 100, date, date, date, "Обрезается текст на стейдже индивидуального предложения", "Обрезается текст на стейдже индивидуального предложения. ");
        int i = tasks.addTask(task);

    }

}
