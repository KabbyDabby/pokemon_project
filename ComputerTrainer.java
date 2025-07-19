package pokemon_project;

import java.util.ArrayList;

public class ComputerTrainer extends PokemonTrainer
{
    // private constants
    // Possible pokemon names and move names to generate random Pokemon
    private static final String[] POKEMON_NAMES = {"Pikachu", "Bulbasaur", "Charmander", "Squirtle"};
	private static final String[] MOVE_NAMES = {"Tailwhip", "Bodyslam", "Splash", "Shock"};
	private static final int MAX_MOVES = 4;
	
	
	// private PokemonImages images = new PokemonImages();

    // Write your ComputerTrainer class here!
    
    // Write a Constructor that sets the name of the ComputerTrainer
    // and adds 2 randomly generated Pokemon to itself
    public ComputerTrainer(String name)
    {
        super(name);
        addRandomPokemon();
    }
    
    /*
     * Adds a randomly generated Pokemon to this ComputerTrainer's
     * collection of Pokemon. A ComputerTrainer can only have 2
     * Pokemon. This method returns true if there was room for the 
     * new Pokemon and it was successfully added, false otherwise.
     */
    public boolean addRandomPokemon()
    {
        int idx1 = (int)(Math.random() * POKEMON_NAMES.length);
        int idx2 = (int)(Math.random() * MOVE_NAMES.length);
        ArrayList<Move> moves1 = new ArrayList<>();
        ArrayList<Move> moves2 = new ArrayList<>();
        for (int i = 0; i < MAX_MOVES; i++) {
            // moves1.add(new Move((int)(Math.random() * MAX_DAMAGE) + 1, MOVE_NAMES[(int) Math.random() * MOVE_NAMES.length]));
            // moves2.add(new Move((int)(Math.random() * MAX_DAMAGE) + 1, MOVE_NAMES[(int) Math.random() * MOVE_NAMES.length]));
        }
        // return super.addPokemon(new Pokemon(POKEMON_NAMES[idx1], idx1, moves1, images.getPokemonImage(POKEMON_NAMES[idx1]))) && 
            //    super.addPokemon(new Pokemon(POKEMON_NAMES[idx2], idx2, moves2, images.getPokemonImage(POKEMON_NAMES[idx2])));
        return false;
    }
    
    
    // Returns a Move randomly chosen from the set of Moves
    // that this trainer's current Pokemon knows.
    // If all Pokemon have fainted, returns null.
    public Move chooseRandomMove()
    {
        Pokemon currentBattlingPokemon = getNextPokemon();
        if (currentBattlingPokemon == null || hasLost()) {
            return null;
        }
        return currentBattlingPokemon.getMove((int)(Math.random() * currentBattlingPokemon.getMoves().size()));
    }
}
