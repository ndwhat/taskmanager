package taskmanager.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TMButton extends JButton {

    private static final long serialVersionUID = 1L;
    private String label;
    private int width;
    private int height;
    private URL img;
    private int padd = 0;
    private int margin = 0;

    public TMButton(String label, int width, int height) {
        this.label = label;
        this.width = width;
        this.height = height;

    }

    public TMButton(String label, int width, int height, URL img) {

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.label = label;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    @Override
    protected void paintComponent(Graphics g) {

        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                Color.BLACK,
                new Point(0, getHeight()),
                Color.BLACK.brighter()));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        setToolTipText(label);

        if (img != null) {
            try {
                Image image = ImageIO.read(img);
                g.drawImage(image, padd, padd, width - margin, height - margin, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.yellow));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.gray));
                repaint();
            }
        });
    }

    public void setPadding(int padd) {
        this.padd = padd;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.height = height;
        size.width = width;
        return size;
    }

}
