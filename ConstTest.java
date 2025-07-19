package pokemon_project;

import java.io.FileNotFoundException;
public class ConstTest {
    public static void main(String[] args) {
        try {
            PokemonConsts.init();
            System.out.println(PokemonConsts.POKEMON_IMAGES.get("MEWTWO"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
