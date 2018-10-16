package taskmanager.Objects.tasks;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable, Comparable<Task> {

    /**
     * @param isActive Флаг активности
     */
    private final boolean isActive;

    /**
     * @param isDone Флаг завершения
     */
    private final boolean isDone;

    /**
     * @param sort сортировка приоритета
     */
    private final int sort;

    /**
     * @param date Дата создания задания
     */
    private final Date dateCreate;

    /**
     * @param date Дата задания с * || null
     * @see Task#dateNeedTo
     */
    private final Date dateNeedFrom;

    /**
     * @param date Дата задания по
     *
     * @see Task#dateNeedFrom
     */
    private final Date dateNeedTo;

    /**
     * Create Task
     *
     * @param isActive
     * @param isDone
     * @param sort
     * @param dateCreate
     * @param dateNeedFrom
     * @param dateNeedTo
     * @param theme
     * @param description
     */
    public Task(boolean isActive, boolean isDone, int sort, Date dateCreate, Date dateNeedFrom, Date dateNeedTo, String theme, String description) {
        this.isActive = isActive;
        this.isDone = isDone;
        this.sort = sort;
        this.dateCreate = dateCreate;
        this.dateNeedFrom = dateNeedFrom;
        this.dateNeedTo = dateNeedTo;
        this.theme = theme;
        this.description = description;
    }

    /**
     * @param date Тема задания
     */
    private final String theme;

    /**
     * @param description Описание задания
     */
    private final String description;

    public boolean isIsActive() {
        return isActive;
    }

    public boolean isIsDone() {
        return isDone;
    }

    public int getSort() {
        return sort;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public Date getDateNeedFrom() {
        return dateNeedFrom;
    }

    public Date getDateNeedTo() {
        return dateNeedTo;
    }

    @Override
    public int compareTo(Task o) {
        if (this.sort < o.getSort()) {
            return 1;
        } else if (this.sort == o.getSort()) {
            return this.dateNeedTo.compareTo(o.getDateNeedTo());
        } else {
            return -1;
        }
    }

    public String getTheme() {
        return theme;
    }

    public String getDescription() {
        return description;
    }

}
