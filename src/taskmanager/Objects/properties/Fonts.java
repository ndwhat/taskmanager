package taskmanager.Objects.properties;

import java.awt.*;

public enum Fonts {
    SMALL("Arial", Font.BOLD, 13),
    P("Arial", Font.BOLD, 16),
    H3("Arial", Font.BOLD, 20),
    H2("Arial", Font.BOLD, 24),
    H1("Arial", Font.BOLD, 28);

    private String name;
    private int style;
    private int size;

    Fonts(String name, int style, int size) {
        this.name = name;
        this.style = style;
        this.size = size;
    }

    public Font getFont() {

        return new Font(this.name, this.style, this.size);
    }
}
