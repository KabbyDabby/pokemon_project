import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;
public class PokemonConsts {

    public static final HashMap<AttackKey, Double> ATTACK_MULTIPLIER = new HashMap<>();
    static {
        List<String> types = Arrays.asList(
            "NORMAL", "FIRE", "WATER", "ELECTRIC", "GRASS", "ICE", "FIGHTING", "POISON",
            "GROUND", "FLYING", "PSYCHIC", "BUG", "ROCK", "GHOST", "DRAGON", "DARK", "STEEL", "FAIRY"
        );
        // Set all to neutral (1.0)
        for (String atk : types) {
            for (String def : types) {
                ATTACK_MULTIPLIER.put(new AttackKey(atk, def), 1.0);
            }
        }
        // Now override with actual chart values
        ATTACK_MULTIPLIER.put(new AttackKey("NORMAL", "ROCK"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("NORMAL", "GHOST"), 0.0);
        ATTACK_MULTIPLIER.put(new AttackKey("NORMAL", "STEEL"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "FIRE"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "WATER"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "GRASS"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "ICE"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "BUG"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "ROCK"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "DRAGON"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIRE", "STEEL"), 2.0);

        ATTACK_MULTIPLIER.put(new AttackKey("WATER", "FIRE"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("WATER", "WATER"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("WATER", "GRASS"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("WATER", "GROUND"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("WATER", "ROCK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("WATER", "DRAGON"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("ELECTRIC", "WATER"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ELECTRIC", "ELECTRIC"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("ELECTRIC", "GRASS"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("ELECTRIC", "GROUND"), 0.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ELECTRIC", "FLYING"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ELECTRIC", "DRAGON"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "FIRE"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "WATER"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "GRASS"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "POISON"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "GROUND"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "FLYING"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "BUG"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "ROCK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "DRAGON"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GRASS", "STEEL"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("ICE", "FIRE"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("ICE", "WATER"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("ICE", "GRASS"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ICE", "GROUND"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ICE", "FLYING"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ICE", "DRAGON"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ICE", "STEEL"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "NORMAL"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "ICE"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "POISON"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "FLYING"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "PSYCHIC"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "BUG"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "ROCK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "GHOST"), 0.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "DARK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "STEEL"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FIGHTING", "FAIRY"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("POISON", "GRASS"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("POISON", "POISON"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("POISON", "GROUND"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("POISON", "ROCK"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("POISON", "GHOST"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("POISON", "STEEL"), 0.0);
        ATTACK_MULTIPLIER.put(new AttackKey("POISON", "FAIRY"), 2.0);

        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "FIRE"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "ELECTRIC"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "GRASS"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "POISON"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "FLYING"), 0.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "BUG"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "ROCK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GROUND", "STEEL"), 2.0);

        ATTACK_MULTIPLIER.put(new AttackKey("FLYING", "GRASS"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FLYING", "ELECTRIC"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FLYING", "FIGHTING"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FLYING", "BUG"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FLYING", "ROCK"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FLYING", "STEEL"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("PSYCHIC", "FIGHTING"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("PSYCHIC", "POISON"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("PSYCHIC", "PSYCHIC"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("PSYCHIC", "DARK"), 0.0);
        ATTACK_MULTIPLIER.put(new AttackKey("PSYCHIC", "STEEL"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "FIRE"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "GRASS"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "FIGHTING"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "POISON"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "FLYING"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "PSYCHIC"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "GHOST"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "DARK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "STEEL"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("BUG", "FAIRY"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("ROCK", "FIRE"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ROCK", "ICE"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ROCK", "FIGHTING"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("ROCK", "GROUND"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("ROCK", "FLYING"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ROCK", "BUG"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("ROCK", "STEEL"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("GHOST", "NORMAL"), 0.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GHOST", "PSYCHIC"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GHOST", "GHOST"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("GHOST", "DARK"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("DRAGON", "DRAGON"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("DRAGON", "STEEL"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("DRAGON", "FAIRY"), 0.0);

        ATTACK_MULTIPLIER.put(new AttackKey("DARK", "PSYCHIC"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("DARK", "GHOST"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("DARK", "DARK"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("DARK", "FAIRY"), 0.5);

        ATTACK_MULTIPLIER.put(new AttackKey("STEEL", "FIRE"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("STEEL", "WATER"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("STEEL", "ELECTRIC"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("STEEL", "ICE"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("STEEL", "ROCK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("STEEL", "STEEL"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("STEEL", "FAIRY"), 2.0);

        ATTACK_MULTIPLIER.put(new AttackKey("FAIRY", "FIRE"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FAIRY", "FIGHTING"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FAIRY", "POISON"), 0.5);
        ATTACK_MULTIPLIER.put(new AttackKey("FAIRY", "DRAGON"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FAIRY", "DARK"), 2.0);
        ATTACK_MULTIPLIER.put(new AttackKey("FAIRY", "STEEL"), 0.5);
    }

    public static final HashSet<String> TYPES = new HashSet<>(
        Arrays.asList(
            "NORMAL",
            "FIRE",
            "WATER",
            "ELECTRIC",
            "GRASS",
            "ICE",
            "FIGHTING",
            "POISON",
            "GROUND",
            "FLYING",
            "PSYCHIC",
            "BUG",
            "ROCK",
            "GHOST",
            "DRAGON",
            "DARK",
            "STEEL",
            "FAIRY",
            "STELLAR"
        )
    );
    public static final HashSet<String> POKEMON_NAMES = new HashSet<>();
    public static final HashMap<String,String> TYPE_1 = new HashMap<>();
    public static final HashMap<String,String> TYPE_2 = new HashMap<>();
    public static final HashMap<String,Integer> HP = new HashMap<>();
    public static final HashMap<String,Integer> ATTACK = new HashMap<>();
    public static final HashMap<String,Integer> SPECIAL_ATTACK = new HashMap<>();
    public static final HashMap<String,Integer> DEFENSE = new HashMap<>();
    public static final HashMap<String,Integer> SPECIAL_DEFENSE = new HashMap<>();
    public static final HashMap<String,Integer> SPEED = new HashMap<>();
    
    private static Scanner names;

    public static final HashSet<String> MOVE_NAMES = new HashSet<>();
    public static final HashMap<String, Integer> MOVE_POWER = new HashMap<>();
    public static final HashMap<String, Double> MOVE_ACCURACY = new HashMap<>();
    public static final HashMap<String, String> MOVE_TYPE = new HashMap<>();
    public static final HashMap<String, String> MOVE_PHYSICAL_SPECIAL = new HashMap<>();
    public static final HashMap<String, Integer> MOVE_PP = new HashMap<>();

    public static final HashMap<String, String> POKEMON_IMAGES = new HashMap<>();

    private static Scanner moves;

    public static void init() throws FileNotFoundException{
        names = new Scanner(new File("PokemonData.txt"));
        moves = new Scanner(new File("moves.txt"));
        Scanner asciiImages = new Scanner(new File("pokemonImages.txt"));
        while (names.hasNextLine()) {
            String[] line = names.nextLine().split(",");
            POKEMON_NAMES.add(line[0].toUpperCase());
            TYPE_1.put(line[0].toUpperCase(), line[1].toUpperCase());
            if (!line[2].equals("")) {
                TYPE_2.put(line[0].toUpperCase(), line[2].toUpperCase());
            }
            HP.put(line[0].toUpperCase(), Integer.parseInt(line[3]));
            ATTACK.put(line[0].toUpperCase(), Integer.parseInt(line[4]));
            SPECIAL_ATTACK.put(line[0].toUpperCase(), Integer.parseInt(line[6]));
            DEFENSE.put(line[0].toUpperCase(), Integer.parseInt(line[5]));
            SPECIAL_DEFENSE.put(line[0].toUpperCase(), Integer.parseInt(line[7]));
            SPEED.put(line[0].toUpperCase(), Integer.parseInt(line[8]));
        }

        while (moves.hasNextLine()) {
            String[] line = moves.nextLine().split(",");
            if (!line[6].toUpperCase().equals("STATUS")) {
                MOVE_NAMES.add(line[2].toUpperCase());
                MOVE_POWER.put(line[2].toUpperCase(), Integer.parseInt(line[4]));
                MOVE_ACCURACY.put(line[2].toUpperCase(), Integer.parseInt(line[7])/100.0);
                MOVE_TYPE.put(line[2].toUpperCase(), line[5].toUpperCase());
                MOVE_PHYSICAL_SPECIAL.put(line[2].toUpperCase(), line[6].toUpperCase());
                MOVE_PP.put(line[2].toUpperCase(), Integer.parseInt(line[8]));
            }
        }

        // Parse pokemonImages.txt to store ASCII art
        StringBuilder currentImage = new StringBuilder();
        String currentPokemon = null;
        List<String> imageLines = new ArrayList<>();

        while (asciiImages.hasNextLine()) {
            String line = asciiImages.nextLine();
            
            // Check if this is a header line (starts with ##)
            if (line.startsWith("##")) {
                // If we were building an image, save it
                if (currentPokemon != null) {
                    // Join all lines with newlines, preserving exact whitespace
                    String image = String.join("\n", imageLines);
                    POKEMON_IMAGES.put(currentPokemon.toUpperCase(), image);
                }
                
                // Start new image
                currentPokemon = line.substring(7); // Remove ## prefix and pokedex number
                imageLines.clear();
            } else {
                // Add line to current image, preserving all whitespace
                imageLines.add(line);
            }
        }
        
        // Don't forget to save the last image
        if (currentPokemon != null) {
            String image = String.join("\n", imageLines);
            POKEMON_IMAGES.put(currentPokemon.toUpperCase(), image);
        }
        
        asciiImages.close();
    }
}
