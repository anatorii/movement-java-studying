import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Movement extends JFrame {
    static int width = 810;
    static int height = 620;
    private int clientWidth;
    private int clientHeight;
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
        setResizable(false);
        setLayout(null);

        panel = new JPanel();
        panel.setBackground(Color.orange);
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.setFocusable(true);
        getContentPane().add(panel);


        objectIcon = ImageIO.read(new File("object-50.jpg"));

        label = new JLabel();
        label.setIcon(new ImageIcon(objectIcon));
        label.setBounds(0, 0, objectIcon.getWidth(), objectIcon.getHeight());

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int horShift = 0;
                int verShift = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        horShift = -50;
                        break;
                    case KeyEvent.VK_RIGHT:
                        horShift = 50;
                        break;
                    case KeyEvent.VK_UP:
                        verShift = -50;
                        break;
                    case KeyEvent.VK_DOWN:
                        verShift = 50;
                        break;
                }
                int newX = label.getX() + horShift;
                int newY = label.getY() + verShift;
                if (newX >= 0 && newX <= (clientWidth - label.getWidth()) && newY >= 0 && newY <= (clientHeight - label.getHeight())) {
                    label.setBounds(label.getX() + horShift, label.getY() + verShift, label.getWidth(), label.getHeight());
                }
            }
        });

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

                    frame.clientWidth = Movement.width;
                    frame.clientHeight = Movement.height;
                    if (frame.isResizable()) {
                        frame.clientWidth = frame.getContentPane().getWidth();
                        frame.clientHeight = frame.getContentPane().getHeight();
                    }

                    frame.panel.setBounds(0, 0, frame.clientWidth, frame.clientHeight);
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
    }
}
