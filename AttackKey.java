public class AttackKey {
    private final String attackingMoveType;
    private final String receivingPokemonType;

    public AttackKey(String attackingMoveType, String receivingPokemonType) {
        this.attackingMoveType = attackingMoveType;
        this.receivingPokemonType = receivingPokemonType;
    }

    public String attackingMoveType() {
        return attackingMoveType;
    }

    public String receivingPokemonType() {
        return receivingPokemonType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttackKey that = (AttackKey) o;
        return attackingMoveType.equals(that.attackingMoveType) &&
            receivingPokemonType.equals(that.receivingPokemonType);
    }

    @Override
    public int hashCode() {
        return 31 * attackingMoveType.hashCode() + receivingPokemonType.hashCode();
    }

    @Override
    public String toString() {
        return "AttackKey[attackingMoveType=" + attackingMoveType + ", receivingPokemonType=" + receivingPokemonType + "]";
    }
}
