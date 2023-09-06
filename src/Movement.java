import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movement extends JFrame {
    static int width = 800;
    static int height = 600;
    BufferedImage objectIcon;
    JPanel panel;
    JLabel label;
    public Movement() throws IOException {
        super("Simple movement");
        initGui();
    }
    public void initGui() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(Movement.width, Movement.height));
        setLocation((d.width - Movement.width) / 2, (d.height - Movement.height) / 2);
        getContentPane().setBackground(Color.green);

        panel = new JPanel();
        getContentPane().add(panel);
        panel.setFocusable(true);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.orange);

        objectIcon = ImageIO.read(new File("object-50.jpg"));
        label = new JLabel();
        label.setIcon(new ImageIcon(objectIcon));
        panel.add(label);
    }
    static public void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Movement frame = new Movement();
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.label.setBounds(0, 0, frame.objectIcon.getWidth(), frame.objectIcon.getHeight());


                    System.out.println(frame.panel.getWidth());
                    System.out.println(frame.panel.getHeight());
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
    }
}
