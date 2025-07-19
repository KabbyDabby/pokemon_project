package pokemon_project;
import java.util.Scanner;
import java.util.ArrayList;
// import java.util.HashSet;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.HashMap;
public class PokemonSimulation
{
    // private PokemonImages images = new PokemonImages();
    Scanner input = new Scanner(System.in);
    private PokemonTrainer trainer1;
    private PokemonTrainer trainer2;

    
    public void run()
    {
        try {
            PokemonConsts.init();
            System.out.println("Welcome to the Pokémon Simulation!");
            System.out.println("--------------------------------");
            for (int trainerIdx = 1; trainerIdx <= 2; trainerIdx++) {
                System.out.print("Enter the name of trainer " + trainerIdx + ": ");
                PokemonTrainer trainer = new PokemonTrainer(input.nextLine());
                System.out.println();
                if (trainerIdx == 1) {
                    trainer1 = trainer;
                } else {
                    trainer2 = trainer;
                }
                
                int numPokemon;
                while (true) {
                    try {
                        System.out.print("Enter the number of Pokémon for trainer " + trainer + ": ");
                        numPokemon = input.nextInt();
                        input.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        input.nextLine();
                        System.out.println("Please enter a valid integer.");
                    }
                }
                System.out.println();
                numPokemon = Math.min(numPokemon, PokemonTrainer.MAX_POKEMON);
                for (int i = 0; i < numPokemon; i++) {
                    String name;
                    while (true) {
                        System.out.print("Enter the name of Pokémon " + (i + 1) + " for trainer " + trainer + ": ");
                        name = input.nextLine();
                        if (PokemonConsts.POKEMON_NAMES.contains(name.toUpperCase())) {
                            break;
                        }
                        System.out.println("Invalid Pokémon name! Please try again.");
                    }
                    System.out.println();
                    int numMoves;
                    while (true) {
                        try {
                            System.out.print("How many moves does " + name + " know: ");
                            numMoves = input.nextInt();
                            if (numMoves < 0 || numMoves > Pokemon.MAX_MOVES) {
                                throw new InputMismatchException();
                            }
                            input.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            input.nextLine();
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    System.out.println();
                    numMoves = Math.min(numMoves, Pokemon.MAX_MOVES);
                    ArrayList<Move> moves = new ArrayList<>();
                    for (int j = 0; j < numMoves; j++) {
                        String moveName;
                        while (true) {
                            System.out.print("Enter the name of move " + (j + 1) + " for " + name + ": ");
                            moveName = input.nextLine().toUpperCase();
                            if (PokemonConsts.MOVE_NAMES.contains(moveName)) {
                                break;
                            }
                            System.out.println("Invalid move! Please try again.");
                        }
                        System.out.println();
                        moves.add(new Move(PokemonConsts.MOVE_POWER.get(moveName), moveName, PokemonConsts.MOVE_TYPE.get(moveName), PokemonConsts.MOVE_ACCURACY.get(moveName), PokemonConsts.MOVE_PP.get(moveName), PokemonConsts.MOVE_PHYSICAL_SPECIAL.get(moveName).equals("PHYSICAL")));
                    }
                    Pokemon pokemon;
                    if (PokemonConsts.POKEMON_IMAGES.get(name.toUpperCase()) == null) {
                        pokemon = new Pokemon(name.toUpperCase(), PokemonConsts.HP.get(name.toUpperCase()), moves);
                    }
                    else {
                        pokemon = new Pokemon(name.toUpperCase(), PokemonConsts.HP.get(name.toUpperCase()), moves, PokemonConsts.POKEMON_IMAGES.get(name.toUpperCase()));
                    }
                    trainer.addPokemon(pokemon);
                    System.out.println(trainer + " has chosen " + pokemon);
                }
            }
            
            // Start the battle
        
            Pokemon currentPokemon1 = trainer1.getNextPokemon();
            Pokemon currentPokemon2 = trainer2.getNextPokemon();

            System.out.println("\n" + trainer1 + " and " + trainer2 + " will now battle:");

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------------------------------");

            System.out.println(currentPokemon1 + " vs " + currentPokemon2);


            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("--------------------------------");
            
            while (!trainer1.hasLost() && !trainer2.hasLost()) {
                if (PokemonConsts.SPEED.get(currentPokemon2.getName()) > PokemonConsts.SPEED.get(currentPokemon1.getName())) {
                    Pokemon tempPokemon = currentPokemon1;
                    currentPokemon1 = currentPokemon2;
                    currentPokemon2 = tempPokemon;

                    PokemonTrainer tempTrainer = trainer1;
                    trainer1 = trainer2;
                    trainer2 = tempTrainer;
                }

                //P1's turn
                System.out.println("\n" + trainer1 + "'s turn!");
                System.out.println("Current Pokemon: " + currentPokemon1 + "\n");

                int moveNumber;
                while (true) {
                    System.out.println("Available moves/actions:");
                    System.out.println("(0) -- Switch Pokémon");
                    for (int i = 0; i < currentPokemon1.getMoves().size(); i++) {
                        System.out.println("(" + (i+1) + ") -- " + currentPokemon1.getMove(i) + " -- " + currentPokemon1.getEffectivity(currentPokemon2, currentPokemon1.getMove(i)));
                    }

                    System.out.println();
                    
                    while (true) {
                        try {
                            System.out.print("Choose a move/action (enter the number):");
                            moveNumber = input.nextInt();
                            input.nextLine();

                            if ((moveNumber - 1 < -1 || moveNumber - 1 >= currentPokemon1.getMoves().size()) || (moveNumber != 0 && currentPokemon1.getMove(moveNumber-1).getPP() <= 0)) {
                                throw new InputMismatchException();
                            }
                            break;
                        } catch (InputMismatchException e) {
                            input.nextLine();
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    System.out.println();

                    HashMap<Integer, Integer> switchablePokemonMap = new HashMap<>();

                    if (moveNumber == 0) {
                        int switchablePokemon = 0;
                        for (int i = 0; i < trainer1.getTeam().size(); i++) {
                            if (!trainer1.getTeam().get(i).hasFainted() && !trainer1.getTeam().get(i).equals(currentPokemon1)) {
                                switchablePokemon++;
                            }
                        }
                        if (switchablePokemon == 0) {
                            System.out.println("No switchable Pokémon!\n");
                            continue;
                        } else {
                            System.out.println("Switchable Pokémon:");
                            System.out.println("(0) -- Select a move for " + currentPokemon1.getName());
                            int switchablePokemonIndex = 1;
                            for (int i = 0; i < trainer1.getTeam().size(); i++) {
                                if (!trainer1.getTeam().get(i).hasFainted() && !trainer1.getTeam().get(i).equals(currentPokemon1)) {
                                    System.out.println("(" + (switchablePokemonIndex) + ") -- " + trainer1.getTeam().get(i).getName());
                                    switchablePokemonMap.put(switchablePokemonIndex, i);
                                    switchablePokemonIndex++;
                                }
                            }
                        }

                        System.out.println();

                        int switchNumber;
                        loop: {
                            while (true) {
                                try {
                                    System.out.print("Choose a Pokémon to switch to (enter the number): ");
                                    switchNumber = input.nextInt();
                                    input.nextLine();
                                    if (switchNumber == 0) {
                                        break;
                                    }
                                    if (switchablePokemonMap.containsKey(switchNumber)) {
                                        break loop;
                                    }
                                } catch (InputMismatchException e) {
                                    input.nextLine();
                                    System.out.println("Please enter a valid integer.");
                                }
                            }
                            System.out.println();
                            continue;
                        }
                        currentPokemon1 = trainer1.getTeam().get(switchablePokemonMap.get(switchNumber));
                        System.out.println(trainer1 + " sent out " + currentPokemon1 + "!");
                        break;
                    }
                    else {
                        Move move = currentPokemon1.getMove(moveNumber-1);
                        System.out.println(currentPokemon1.getName() + " used " + move.getName() + "!");
                        System.out.println(currentPokemon2.getName() + " took " + currentPokemon1.attack(currentPokemon2, move) + " damage!");
                        if (!currentPokemon1.getLastEffectivity().toLowerCase().equals("it was effective.")) {
                            System.out.println(currentPokemon1.getLastEffectivity());
                        }
                        if (currentPokemon1.getLastCritical() != "") {
                            System.out.println(currentPokemon1.getLastCritical());
                        }
                        break;
                    }
                }

                System.out.println();


                if (trainer2.hasLost()) {
                    break;
                }
                
                if (currentPokemon2.hasFainted()) {
                    System.out.println(currentPokemon2.getName() + " fainted!");

                    System.out.println(trainer2 + ", choose your next Pokémon:");

                    int nextPokemonIdx = 1;
                    HashMap<Integer, Integer> nextPokemonMap = new HashMap<>();
                    for (int i = 0; i < trainer2.getTeam().size(); i++) {
                        if (!trainer2.getTeam().get(i).hasFainted()) {
                            System.out.println("(" + (nextPokemonIdx) + ") -- " + trainer2.getTeam().get(i).getName());
                            nextPokemonMap.put(nextPokemonIdx, i);
                            nextPokemonIdx++;
                        }
                    }

                    int nextPokemonNumber;
                    while (true) {
                        if (nextPokemonMap.size() == 1) {
                            nextPokemonNumber = 1;
                            System.out.println("Only one Pokémon left, " + trainer2.getTeam().get(nextPokemonMap.get(1)).getName() + " will be sent out!");
                            break;
                        }

                        try {
                            System.out.print("Choose a Pokémon to switch to (enter the number):");
                            nextPokemonNumber = input.nextInt();
                            if (!nextPokemonMap.containsKey(nextPokemonNumber)) {
                                throw new InputMismatchException();
                            }
                            break;
                        } catch (InputMismatchException e) {
                            input.nextLine();
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    
                    currentPokemon2 = trainer2.getTeam().get(nextPokemonMap.get(nextPokemonNumber));

                    if (currentPokemon2 != null) {
                        System.out.println(trainer2 + " sent out " + currentPokemon2.getName() + "!");
                    }
                    continue;
                }
                
                
                //P2's turn
                System.out.println();
                System.out.println("\n" + trainer2 + "'s turn!");
                System.out.println("Current Pokémon: " + currentPokemon2 + "\n");

                while (true) {

                    System.out.println("Available moves/actions:");
                    System.out.println("(0) -- Switch Pokémon");
                    for (int i = 0; i < currentPokemon2.getMoves().size(); i++) {
                        System.out.println("(" + (i+1) + ") -- " + currentPokemon2.getMove(i) + " -- " + currentPokemon2.getEffectivity(currentPokemon1, currentPokemon2.getMove(i)));
                    }
                    
                    while (true) {
                        try {
                            System.out.print("Choose a move/action (enter the number): ");
                            moveNumber = input.nextInt();
                            input.nextLine();

                            if ((moveNumber - 1 < -1 || moveNumber - 1 >= currentPokemon2.getMoves().size()) || (moveNumber != 0 && currentPokemon2.getMove(moveNumber-1).getPP() <= 0)) {
                                throw new InputMismatchException();
                            }
                            break;
                        } catch (InputMismatchException e) {
                            input.nextLine();
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    System.out.println();

                    HashMap<Integer, Integer> switchablePokemonMap = new HashMap<>();

                    if (moveNumber == 0) {
                        int switchablePokemon = 0;
                        for (int i = 0; i < trainer2.getTeam().size(); i++) {
                            if (!trainer2.getTeam().get(i).hasFainted() && !trainer2.getTeam().get(i).equals(currentPokemon2)) {
                                switchablePokemon++;
                            }
                        }
                        if (switchablePokemon == 0) {
                            System.out.println("No switchable Pokémon!");
                            continue;
                        } else {
                            System.out.println("Switchable Pokémon:");
                            System.out.println("(0) -- Select move for " + currentPokemon2.getName() + "!");
                            int switchablePokemonIndex = 1;
                            for (int i = 0; i < trainer2.getTeam().size(); i++) {
                                if (!trainer2.getTeam().get(i).hasFainted() && !trainer2.getTeam().get(i).equals(currentPokemon2)) {
                                    System.out.println("(" + (switchablePokemonIndex) + ") -- " + trainer2.getTeam().get(i).getName());
                                    switchablePokemonMap.put(switchablePokemonIndex, i);
                                    switchablePokemonIndex++;
                                }
                            }
                        }
                        int switchNumber;
                        loop: {
                            while (true) {
                                try {
                                    System.out.print("Choose a Pokémon to switch to (enter the number): ");
                                    switchNumber = input.nextInt();
                                    input.nextLine();
                                    if (switchNumber == 0) {
                                        break;
                                    }
                                    if (switchablePokemonMap.containsKey(switchNumber)) {
                                        break loop;
                                    }
                                } catch (InputMismatchException e) {
                                    input.nextLine();
                                    System.out.println("Please enter a valid integer.");
                                }
                            }
                            continue;
                        }
                        System.out.println();
                        currentPokemon2 = trainer2.getTeam().get(switchablePokemonMap.get(switchNumber));
                        System.out.println(trainer2 + " sent out " + currentPokemon2.getName() + "!");
                        break;
                    }
                    else {
                        Move move = currentPokemon2.getMove(moveNumber-1);
                        System.out.println(currentPokemon2.getName() + " used " + move.getName() + "!");
                        System.out.println(currentPokemon1.getName() + " took " + currentPokemon2.attack(currentPokemon1, move) + " damage!");
                        if (!currentPokemon2.getLastEffectivity().toLowerCase().equals("it was effective.")) {
                            System.out.println(currentPokemon2.getLastEffectivity());
                        }
                        if (currentPokemon2.getLastCritical() != "") {
                            System.out.println(currentPokemon2.getLastCritical());
                        }
                        break;
                    }
                }
                System.out.println(currentPokemon1);

                if (trainer1.hasLost()) {
                    break;
                }
                
                if (currentPokemon1.hasFainted()) {
                    System.out.println(currentPokemon1.getName() + " fainted!");
                    System.out.println(trainer1 + ", choose your next Pokémon:");

                    int nextPokemonIdx = 1;
                    HashMap<Integer, Integer> nextPokemonMap = new HashMap<>();
                    for (int i = 0; i < trainer1.getTeam().size(); i++) {
                        if (!trainer1.getTeam().get(i).hasFainted()) {
                            System.out.println("(" + (nextPokemonIdx) + ") -- " + trainer1.getTeam().get(i).getName());
                            nextPokemonMap.put(nextPokemonIdx, i);
                            nextPokemonIdx++;
                        }
                    }

                    int nextPokemonNumber;
                    while (true) {
                        if (nextPokemonMap.size() == 1) {
                            nextPokemonNumber = 1;
                            System.out.println("Only one Pokémon left, " + trainer1.getTeam().get(nextPokemonMap.get(1)).getName() + " will be sent out!");
                            break;
                        }

                        try {
                            System.out.print("Choose a Pokémon to switch to (enter the number):");
                            nextPokemonNumber = input.nextInt();
                            if (!nextPokemonMap.containsKey(nextPokemonNumber)) {
                                throw new InputMismatchException();
                            }
                            break;
                        } catch (InputMismatchException e) {
                            input.nextLine();
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    
                    currentPokemon1 = trainer1.getTeam().get(nextPokemonMap.get(nextPokemonNumber));

                    if (currentPokemon1 != null) {
                        System.out.println(trainer1 + " sent out " + currentPokemon1.getName() + "!");
                    }
                }
            }
            
            if (trainer1.hasLost()) {
                System.out.println("\n" + trainer2 + " wins!");
                System.out.println("Their team was:");
                for (Pokemon pokemon : trainer2.getTeam()) {
                    System.out.println(pokemon);
                }
            } else {
                System.out.println("\n" + trainer1 + " wins!");
                System.out.println("Their team was:");
                for (Pokemon pokemon : trainer1.getTeam()) {
                    System.out.println(pokemon);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Pokémon data file not found!");
            return;
        }
    }
}
