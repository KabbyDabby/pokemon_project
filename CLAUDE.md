# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **Pokémon Battle Simulation** written in Java. It's a console-based, turn-based battle system where two trainers battle with their Pokémon teams. The project includes ASCII art visualization for Pokémon and comprehensive battle mechanics including type effectiveness, critical hits, and move accuracy.

## Architecture

### Core Classes
- **`PokemonSimulation.java`** - Main game loop and user interaction controller
- **`Pokemon.java`** - Core Pokémon class with battle mechanics, health, moves, and type calculations
- **`Move.java`** - Represents individual moves with power, accuracy, PP, and type
- **`PokemonTrainer.java`** - Manages trainer teams (max 4 Pokémon)
- **`PokemonConsts.java`** - Contains all static data (stats, type chart, moves) loaded from data files
- **`AttackKey.java`** - Helper class for type effectiveness lookups
- **`RunSimulation.java`** - Entry point with main method

### Data Files
- **`PokemonData.txt`** - Contains all Pokémon with stats (HP, Attack, Defense, etc.) and types
- **`moves.txt`** - Move database with power, accuracy, PP, and type information
- **`pokemonImages.txt`** - ASCII art representations of Pokémon
- **`Pokédex Images/`** - PNG images of Pokémon (1025 files, generations 1-9)

### Go Module
- **`pokemon_ascii/`** - Contains a Go module for ASCII image conversion using external libraries

## Development Commands

### Java Compilation and Execution
```bash
# Compile all Java files
javac *.java

# Run the simulation
java RunSimulation

# Run specific class with package
java pokemon_project.RunSimulation
```

### Go Module (ASCII converter)
```bash
# Navigate to Go module
cd pokemon_ascii

# Install dependencies
go mod tidy

# Build
go build
```

## Key Features
- Type effectiveness system with 18 Pokémon types
- Critical hit mechanics (1/24 chance)
- Move accuracy and PP system
- ASCII art display for Pokémon
- Switching between Pokémon during battle
- Health and stat calculations based on level 50 formulas

## File Structure Notes
- Data files (`PokemonData.txt`, `moves.txt`, `pokemonImages.txt`) loaded using relative paths
- No package structure in root - all Java files use `package pokemon_project;`
- Images stored both as PNG files and ASCII text format
- Battle mechanics closely follow official Pokémon game formulas

## Important Constants
- `Pokemon.MAX_MOVES = 4` - Maximum moves per Pokémon
- `PokemonTrainer.MAX_POKEMON = 4` - Maximum Pokémon per trainer
- Health calculation: `baseStat + 107` (level 50 formula)
- Attack/Defense calculations include level 50 adjustments (+107)