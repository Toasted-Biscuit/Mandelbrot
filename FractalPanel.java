import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class FractalPanel extends JPanel implements MouseListener, KeyListener{

    int width = 800;
    int height = 600;
    int colorScheme = 1;
    Color[][] pixels;
    Palette palette;
    View view;
    Complex topLeft;
    
    public FractalPanel() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        palette = new Palette(colorScheme);
        view = new View(width, height);
        pixels = new Color[height][width];
        addKeyListener(this);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double n = Mandelbrot.testPoint(view.translate(x, y));
                n /= Mandelbrot.iterationLimit;
                pixels[y][x] = palette.getColor(n);
                g.setColor(pixels[y][x]);
                g.fillRect(x, y, 1, 1);
            }
        }
    }

    public void saveImage(String location) {
        BufferedImage img = new BufferedImage(pixels[0].length, pixels.length, 1);
            for (int r = 0; r < pixels.length; r++) {
                for (int c = 0; c < pixels[r].length; c++) {
                    img.setRGB(c, r, pixels[r][c].getRGB());
                }
            }
            try {
                ImageIO.write(img, "png", new File(location + ".png"));
                System.out.println("Saved Image at " + location + ".png");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Unused
    }

    @Override
    public void mousePressed(MouseEvent e) {
        topLeft = view.translate(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Complex botRight = view.translate(e.getX(), e.getY());
        view.setComplexCorners(topLeft, botRight);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Unused
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Unused
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Increase Resoultion
        if (e.getKeyChar() == 'p') {
            if (Mandelbrot.iterationLimit == 0) {
                Mandelbrot.iterationLimit = 1;
            } else {
                Mandelbrot.iterationLimit *= 2;
            }
            System.out.println("Resolution set to " + Mandelbrot.iterationLimit);
        }

        // Decrease resolution
        if (e.getKeyChar() == 'o') {
            Mandelbrot.iterationLimit /= 2;
            System.out.println("Resolution set to " + Mandelbrot.iterationLimit);
        }

        // Reset View
        if (e.getKeyChar() == 'r') {
            view = new View(width, height);
        }

        // Change Color Palette
        if (e.getKeyChar() == 'c') {
            colorScheme++;
            colorScheme %= Palette.TOTAL_PALETTES; 
            palette = new Palette(colorScheme);
            System.out.println("Color Palette Changed to: " + palette.getPaletteName());
        }

        // Save Image
        if (e.getKeyChar() == 's') {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Where would you like to save the file?");
            fc.showSaveDialog(fc);
            saveImage(fc.getSelectedFile().toString());
        }
        
        // Create Image Sequence
        if (e.getKeyChar() == 'x') {
            Point destination = getMousePosition();
            Complex seqTopLeft = view.translate((int) (destination.getX() - (width / 2)), (int) (destination.getY() - (height / 2)));
            Complex seqBotRight = view.translate((int) (destination.getX() + (width / 2)), (int) (destination.getY() + (width / 2)));
            view.setComplexCorners(seqTopLeft, seqBotRight);
            paint(getGraphics());
            saveImage("ImageSequence\\0");
            for (int i = 1; i < 25; i++) {
                seqBotRight = view.translate((int) (width * 0.75), (int) (height * 0.75));
                seqTopLeft = view.translate((int) (width * 0.25), (int) (height * 0.25));
                view.setComplexCorners(seqTopLeft, seqBotRight);
                paint(getGraphics());
                saveImage("ImageSequence\\" + i);
            }
        }
        
        // Pan
        if (e.getKeyChar() == 'm') {
            Point destination = getMousePosition();
            Complex panTopLeft = view.translate((int) (destination.getX() - (width / 2)), (int) (destination.getY() - (height / 2)));
            Complex panBotRight = view.translate((int) (destination.getX() + (width / 2)), (int) (destination.getY() + (width / 2)));
            System.out.println("Destination: " + view.translate((int)destination.getX(), (int)destination.getY()));
            System.out.println("Top Left: " + panTopLeft);
            System.out.println("Bottom Right: " + panBotRight);
            view.setComplexCorners(panTopLeft, panBotRight);
            paint(getGraphics());
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Unused
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }
}