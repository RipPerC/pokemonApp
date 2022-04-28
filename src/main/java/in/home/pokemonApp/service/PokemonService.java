package in.home.pokemonApp.service;

import in.home.pokemonApp.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {

    List<PokemonDTO> getHeaviestPokemon();

    List<PokemonDTO> getHighestPokemon();

    List<PokemonDTO> getMoreBaseExperiencePokemon();
}
