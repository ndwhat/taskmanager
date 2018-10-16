package taskmanager.Objects.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Tasks implements Serializable {

    static int id;
    static Map<Integer, Task> TaskMap = new HashMap<>();

    private static final File FILETASKS = new File("data/tasks.ser");

    public Tasks() {
        if (FILETASKS.exists() && !FILETASKS.isDirectory()) {
            readFile();
        } else {
            try {
                FILETASKS.getParentFile().mkdirs();
                FILETASKS.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Tasks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void readFile() {
        try (FileInputStream fis = new FileInputStream(FILETASKS)) {
            if (fis.available() > 0) {

                ObjectInputStream ois = new ObjectInputStream(fis);
                TaskMap = (Map<Integer, Task>) ois.readObject();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tasks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Tasks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeToFile() {
        try (FileOutputStream fout = new FileOutputStream(FILETASKS)) {
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(TaskMap);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tasks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tasks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Add task to array
     *
     * @param task
     * @return
     */
    public int addTask(Task task) {
        if (TaskMap.size() > 0) {
            Integer id = findEmptySet(1);
            TaskMap.put(id, task);
            writeToFile();
            return id;
        } else {
            TaskMap.put(1, task);
            writeToFile();
            return 1;
        }
    }

    private int findEmptySet(int i) {
        Integer id = TaskMap.keySet().stream().skip(TaskMap.size() - 1).findFirst().orElse(0) + i;
        if (!TaskMap.containsKey(id)) {
            return id;
        }
        return findEmptySet(i + 1);
    }

    /**
     * Update task to array
     *
     * @param key
     * @param task
     * @return
     */
    public int updateTask(int key, Task task) {
        TaskMap.put(key, task);
        writeToFile();
        return id;
    }

    /**
     * Get task from array
     *
     * @param id
     * @return
     */
    public Task getTask(int id) {
        Task task = TaskMap.get(id);
        return task;
    }

    /**
     * Remove task by ID
     *
     * @param id
     */
    public void removeTask(int id) {
        TaskMap.remove(id);
        writeToFile();
    }

    /**
     * Get map of Tasks
     *
     * @return
     */
    public Map<Integer, Task> getMap() {
        Map<Integer, Task> TaskMap2 = TaskMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())

                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));

        return TaskMap2;
    }

    public List<String> getThemes() {

        List<String> al = TaskMap.entrySet().stream().map(e -> e.getValue().getTheme()).distinct().collect(Collectors.toList());
        return al;
    }

    /**
     * Get map of Tasks with limit
     *
     * @param limit
     * @return
     */
    public Map<Integer, Task> getMap(int limit) {
        if (TaskMap.size() > limit) {
            Map<Integer, Task> TaskMap2 = TaskMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .limit(limit)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e2, LinkedHashMap::new));
            return TaskMap2;
        }

        Map<Integer, Task> TaskMap2 = TaskMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));

        return TaskMap2;
    }

}
