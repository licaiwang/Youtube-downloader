
import javax.swing.*;
import java.net.URL;

import java.awt.*;
public class Background extends JPanel{
    private static final long serialVersionUID = 1L;
    String Path = "";
    public Background(String path) {
        super();
        Path = path;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
    }

    private void drawBackground(Graphics g) {
    	URL url = this.getClass().getResource("/res/background.jpg");
        Image image = new ImageIcon(url).getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}