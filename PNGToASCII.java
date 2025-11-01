import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

public class PNGToASCII {
    // ASCII characters from darkest to lightest
    private static final String ASCII_CHARS = "@%#*+=-:. ";
    private static final int TARGET_WIDTH = 40; // Width of ASCII art
    private static final String IMAGES_DIR = "Pokédex Images/";
    private static final String OUTPUT_FILE = "pokemonImages.txt";

    public static void main(String[] args) {
        try {
            // No need to create output directory - writing to current directory
            
            // Create output file
            PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE));
            
            // Get all PNG files from the directory
            File dir = new File(IMAGES_DIR);
            System.out.println("Looking for PNG files in: " + dir.getAbsolutePath());
            
            if (!dir.exists()) {
                System.err.println("Error: Directory does not exist: " + dir.getAbsolutePath());
                return;
            }
            
            File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".png"));
            
            if (files == null || files.length == 0) {
                System.err.println("Error: No PNG files found in directory: " + dir.getAbsolutePath());
                return;
            }
            
            // Sort files by filename (Pokedex order)
            java.util.Arrays.sort(files, (a, b) -> a.getName().compareTo(b.getName()));
            
            System.out.println("Found " + files.length + " PNG files to process");
            
            for (File file : files) {
                try {
                    // Get Pokemon name from filename (remove .png extension)
                    String pokemonName = file.getName().replace(".png", "");
                    System.out.println("Processing: " + pokemonName);
                    
                    // Convert image to ASCII (inverted)
                    String asciiArt = convertToASCII(file);
                    
                    // Write to file in the same format as existing pokemonImages.txt
                    writer.println("##" + pokemonName);
                    writer.println(asciiArt);
                    writer.println(); // Add blank line between Pokemon
                    
                    System.out.println("Successfully processed: " + pokemonName);
                } catch (Exception e) {
                    System.err.println("Error processing file " + file.getName() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            writer.close();
            System.out.println("Conversion complete! ASCII art saved to " + OUTPUT_FILE);
            
        } catch (IOException e) {
            System.err.println("Error during conversion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String convertToASCII(File imageFile) throws IOException {
        BufferedImage image = ImageIO.read(imageFile);
        if (image == null) {
            throw new IOException("Failed to read image file: " + imageFile.getName());
        }
        
        // Calculate aspect ratio to maintain proportions
        double aspectRatio = (double) image.getHeight() / image.getWidth();
        int targetHeight = (int) (TARGET_WIDTH * aspectRatio * 0.5); // Multiply by 0.5 because ASCII characters are taller than wide
        
        // Resize image
        BufferedImage resized = new BufferedImage(TARGET_WIDTH, targetHeight, BufferedImage.TYPE_INT_RGB);
        resized.createGraphics().drawImage(image, 0, 0, TARGET_WIDTH, targetHeight, null);
        
        // Invert ASCII_CHARS for negative effect
        String invertedAscii = new StringBuilder(ASCII_CHARS).reverse().toString();
        
        StringBuilder ascii = new StringBuilder();
        
        // Convert pixels to ASCII (inverted)
        for (int y = 0; y < targetHeight; y++) {
            for (int x = 0; x < TARGET_WIDTH; x++) {
                int pixel = resized.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                
                // Convert to grayscale
                int gray = (r + g + b) / 3;
                
                // If pixel is completely black, output a blank (space)
                if (gray == 0) {
                    ascii.append(' ');
                } else {
                    // Map grayscale value to inverted ASCII character
                    int index = (gray * (invertedAscii.length() - 1)) / 255;
                    ascii.append(invertedAscii.charAt(index));
                }
            }
            ascii.append("\n");
        }
        
        return ascii.toString();
    }
} 