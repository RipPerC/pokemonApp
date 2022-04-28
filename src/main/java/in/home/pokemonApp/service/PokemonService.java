package in.home.pokemonApp.service;

import in.home.pokemonApp.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {

    /**
     * Se obtiene un listado con los 5 pokemons más pesados
     * @return
     */
    List<PokemonDTO> getHeaviestPokemon();

    /**
     * Se obtiene un listado de los 5 pokemons más altos
     * @return
     */
    List<PokemonDTO> getHighestPokemon();

    /**
     * Se obtiene un listado con los 5 pokemons con más experiencia base
     * @return
     */
    List<PokemonDTO> getMoreBaseExperiencePokemon();
}
