package in.home.pokemonApp.service;

import in.home.pokemonApp.bean.PokemonBean;
import in.home.pokemonApp.dto.DataTO;
import in.home.pokemonApp.dto.PokemonIndexTO;

import java.io.IOException;
import java.net.MalformedURLException;

public interface PokeApiService {

 /**
  * Se obtiene objeto con un listado completo de los pokemons activos con las url de acceso.
  * @return DataTO
  * @throws IOException
  */
 DataTO getPokemonDataList() throws IOException;

 /**
  * Se obtienen los datos de un Pokemon a partir de la url de acceso.
  * @param url Dirección de acceso
  * @return PokemonBean
  * @throws IOException
  */
 PokemonBean getPokemonData(String url) throws IOException;

}