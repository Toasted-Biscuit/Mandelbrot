import java.awt.Color;

public class Palette {
    private int colorScheme;
    public static final int TOTAL_PALETTES = 5;

    public Palette(int colorScheme) {
        this.colorScheme = colorScheme;
    }

    public Color getColor(double normedN) {
        if (this.colorScheme == 1) {
            return getPalette1(normedN);
        } else if (this.colorScheme == 2) {
            return getPalette2(normedN);
        } else if (this.colorScheme == 3) {
            return getPalette3(normedN);
        } else if (this.colorScheme == 4) {
            return getPalette4(normedN);
        } else if (this.colorScheme == 5) {
            return getPalette5(normedN);
        } else {
            return getDefaultPalette(normedN);
        }
    }

    private Color getDefaultPalette(double n) {
        if (n < 0) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }
    
    private Color getPalette1(double n) {
        if (n < 0) {
            return Color.BLACK;
        } else {
            int red = (int) (n * n * 255);
            int green = (int) (n * n * n * 255);
            int blue = (int) (-1 * (n - 1) * (n - 1) * (n - 1) * 255);
            return new Color(red, green, blue);
        }
    }

    private Color getPalette2(double n) {
        if (n < 0) {
            return Color.BLACK;
        } else {
            int red = (int) (1000 * ((n - 0.5) * (n - 0.5)));
            int green = (int) (127 * Math.sin(9.5 * n) + 127);
            int blue = (int) (70 * Math.cos(15 * n) + 127);
            return new Color(red, green, blue);
        }
    }
    
    private Color getPalette3(double n) {
        if (n < 0) {
            return Color.BLACK;
        } else {
            int red = (int) (Math.pow(n, 4) * 255);
            int green = (int) (100 * Math.cos(n * 3.5) + 130);
            int blue = (int) (100 * Math.cos(n * 3.5 + 3) + 130);
            return new Color(red, green, blue);
        }
    }

    private Color getPalette4(double n) {
        if (n < 0) {
            return Color.BLACK;
        } else {
            int red = (int) (100 * Math.cos(n * 5 + 6.7) + 130);
            int green = (int) (100 * Math.cos(n * 5 + 3.5) + 130);
            int blue = (int) (130 * Math.cos(n * 3 + 3) + 130);
            return new Color(red, green, blue);
        }
    }

    private Color getPalette5(double n) {
        if (n < 0) {
            return Color.BLACK;
        } else {
            int red = (int) (127 * Math.sin(9 * n + 5) + 127);
            int green = (int) (70 * Math.cos(13 * n + 4) + 127);
            int blue = (int) (1000 * ((n - 0.5) * (n - 0.5)));
            return new Color(red, green, blue);
        }
    }

    public String getPaletteName() {
        if (colorScheme == 1) {
            return "Blue and Gold";
        } else if (colorScheme == 2) {
            return "Pink and Green";
        } else if (colorScheme == 3) {
            return "Green to Purple";
        } else if (colorScheme == 4) {
            return "Rainbow";
        } else {
            return "Black and White";
        }
    }
}