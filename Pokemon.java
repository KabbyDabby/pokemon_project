package pokemon_project;
import java.util.ArrayList;


public class Pokemon
{
    // Private constants
    public static final int MAX_MOVES = 4;

    
    // Write your Pokemon class here

    private String lastEffectivity = "";
    private String lastCritical = "";
    private int health;
    private int maxHealth;
    private String name;
    private String image;
    private ArrayList<Move> moves;

    public Pokemon(String name, int healthStat, ArrayList<Move> moves, String image) {
        this.name = name;
        this.health = healthStat+107; //health at level 50
        this.maxHealth = this.health;
        this.moves = moves;
        this.image = image;
    }

    public Pokemon(String name, int healthStat, ArrayList<Move> moves) {
        this.name = name;
        this.health = healthStat+107; //health at level 50
        this.maxHealth = this.health;
        this.moves = moves;
        this.image = "";
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    
    public boolean hasFainted() {
        return health <= 0;
    }

    public boolean hasMoreMoves() {
        return moves.size() < MAX_MOVES;
    }

    public boolean learnMove(Move move) {
        if (moves.size() >= MAX_MOVES) {
            return false;
        }
        moves.add(move);
        return true;
    }

    public void forgetMove(Move move) {
        moves.remove(move);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String toString() {
        String result = name + " (Health: " + health + " / " + maxHealth + ")";
        if (!image.equals("")) {
            result = "\n" + getImage() + "\n" + result;
        }
        return result;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public boolean knowsMove(Move move) {
        return moves.contains(move);
    }

    public boolean knowsMove(String moveName) {
        for (Move move : moves) {
            if (move.getName().equals(moveName)) {
                return true;
            }
        }
        return false;
    }

    public int takeDamage(int damage, double multiplier) {
        health = Math.max(0, health - (int)(damage*multiplier));
        return (int)(damage*multiplier);
    }

    public String getLastEffectivity() {
        return lastEffectivity;
    }

    public String getLastCritical() {
        return lastCritical;
    }

    public int attack(Pokemon opponent, Move move) {

        if (Math.random()> move.getAccuracy()) {
            lastEffectivity = "It missed!";
            return 0;
        }

        if (!knowsMove(move)) {
            return 0;
        }

        move.useMove();
        lastEffectivity = "It was " + getEffectivity(opponent, move).toLowerCase();

        double multiplier = PokemonConsts.ATTACK_MULTIPLIER.get(new AttackKey(move.getType(), PokemonConsts.TYPE_1.get(opponent.getName())));
        if (PokemonConsts.TYPE_2.get(opponent.getName()) != null) {
            multiplier *= PokemonConsts.ATTACK_MULTIPLIER.get(new AttackKey(move.getType(), PokemonConsts.TYPE_2.get(opponent.getName())));
        }

        multiplier *= PokemonConsts.TYPE_1.get(this.getName()).equals(move.getType()) ? 1.5 : 1.0;

        if (PokemonConsts.TYPE_2.get(this.getName()) != null) {
            multiplier *= PokemonConsts.TYPE_2.get(this.getName()).equals(move.getType()) ? 1.5 : 1.0;
        }
        if (move.isPhysical()) {
            multiplier *= ((double) PokemonConsts.ATTACK.get(this.getName())+107.0)/((double) PokemonConsts.DEFENSE.get(opponent.getName())+107.0);
        }
        else {
            multiplier *= ((double) PokemonConsts.SPECIAL_ATTACK.get(this.getName())+107.0)/((double) PokemonConsts.SPECIAL_DEFENSE.get(opponent.getName())+107.0);
        }

        if (Math.random() < 1.0/24.0) {
            lastCritical = "It was a critical hit!";
            multiplier *= 1.5;
        } else {
            lastCritical = "";
        }

        multiplier *= Math.random()/5 + 0.8;

        return opponent.takeDamage(move.getPower(), multiplier);
    }

    public String getEffectivity(Pokemon opponent, Move move) {
        double multiplier = PokemonConsts.ATTACK_MULTIPLIER.get(new AttackKey(move.getType(), PokemonConsts.TYPE_1.get(opponent.getName())));
        if (PokemonConsts.TYPE_2.get(opponent.getName()) != null) {
            multiplier *= PokemonConsts.ATTACK_MULTIPLIER.get(new AttackKey(move.getType(), PokemonConsts.TYPE_2.get(opponent.getName())));
        }

        if (multiplier > 1.0) {
            return "Super effective!";
        }
        else if (multiplier == 0.0) {
            return "Immune!";
        }
        else if (multiplier < 1.0) {
            return "Not very effective.";
        }
        return "Effective.";
    }

    /**
     * @precondition: moveName is the name of a move that the pokemon knows, use knowsMove to check
     */
    public Move getMove(String moveName) {
        for (Move move : moves) {
            if (move.getName().equals(moveName)) {
                return move;
            }
        }
        return null;
    }

    public Move getMove(int moveNumber) {
        return moves.get(moveNumber);
    }

    public int attack(Pokemon opponent, String moveName) {
        return attack(opponent, getMove(moveName));
    }
}