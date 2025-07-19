package pokemon_project;

public class Move
{
    // Private constants
    // private static final int MAX_DAMAGE = 25;
    
    private int power;
    private String name;
    private String type;
    private double accuracy;
    private int pp;
    private boolean physical;


    public Move(int power, String name, String type, double accuracy, int pp, boolean physical) {
        this.power = power;
        this.name = name;
        this.type = type;
        this.accuracy = accuracy;
        this.pp = pp;
        this.physical = physical;
    }
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    public int getPower() {
        return power;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public boolean isPhysical() {
        return physical;
    }

    public int getPP() {
        return pp;
    }

    public void useMove() {
        pp--;
    }
    
    public String toString() {
        return getName() + " (" + getPower() + " Damage, " + (int)(getAccuracy() * 100) + "% Accuracy, " + getPP() + " PP, " + (isPhysical() ? "Physical" : "Special") + ") -- " + getType();
    }

    public boolean equals(Move other) {
        return this.name.equals(other.name) && this.power == other.power;
    }
}