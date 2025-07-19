package pokemon_project;
import java.util.ArrayList;
public class PokemonTrainer
{
    // private constants
    public static final int MAX_POKEMON = 4;
    
    // Write your PokemonTrainer class here
    private String name;
    private ArrayList<Pokemon> team = new ArrayList<>();

    public PokemonTrainer(String name) {
        this.name = name;
    }

    public boolean addPokemon(Pokemon p) {
        if (team.size() >= MAX_POKEMON) {
            return false;
        }
        team.add(p);
        return true;
    }

    public boolean hasLost() {
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                return false;
            }
        }
        return true;
    }

    public Pokemon getNextPokemon() {
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Pokemon> getTeam() {
        return team;
    }

    public String toString() {
        return name;
    }
}

