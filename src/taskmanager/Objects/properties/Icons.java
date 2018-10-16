package taskmanager.Objects.properties;

import java.awt.*;
import java.io.File;
import java.net.URL;

public enum Icons {
    ADDTASK("/img/addTask.png"),
    EDIT("/img/edit.png"),
    CALLENDAR("/img/callendar.png"),
    NOTSUCCESS("/img/nsucc.png"),
    SUCCESS("/img/succ.png");
    private String path;
    Icons(String path) {
        this.path = path;
    }

    public URL getFile() {
        URL file = getClass().getResource(this.path);
        return !(file.getFile().isEmpty()) ? file : null;
    }
}