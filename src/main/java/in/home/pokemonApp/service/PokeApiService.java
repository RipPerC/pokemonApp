package in.home.pokemonApp.service;

import in.home.pokemonApp.bean.PokemonBean;
import in.home.pokemonApp.dto.DataTO;
import in.home.pokemonApp.dto.PokemonIndexTO;

import java.io.IOException;
import java.net.MalformedURLException;

public interface PokeApiService {

 DataTO getPokemonDataList() throws IOException;

 PokemonBean getPokemonData(String url) throws IOException;

 void savePokemonIndex(PokemonIndexTO pokemonIndexTO);

}