package in.home.pokemonApp.service.impl;

import com.google.gson.Gson;
import in.home.pokemonApp.dto.DataTO;
import in.home.pokemonApp.bean.PokemonBean;
import in.home.pokemonApp.dto.PokemonIndexTO;
import in.home.pokemonApp.repository.PokemonRepository;
import in.home.pokemonApp.service.PokeApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Service
public class PokeApiServiceImpl implements PokeApiService {

    private PokemonRepository pokemonRepository;

    public PokeApiServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    private String POKEMON_LIST = "https://pokeapi.co/api/v2/pokemon?limit=10000";

    @Override
    public DataTO getPokemonDataList() throws IOException {
        DataTO genericData = new DataTO();

        URL url = new URL(POKEMON_LIST);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {

            Scanner scanner = new Scanner(url.openStream());
            StringBuffer informationString = new StringBuffer();
            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            Gson g = new Gson();
            genericData = g.fromJson(informationString.toString(), DataTO.class);

        }
        return genericData;
    }


    @Override
    public PokemonBean getPokemonData(String url) throws IOException {
        PokemonBean pokemon = new PokemonBean();

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            Scanner scanner = new Scanner(u.openStream());
            while (scanner.hasNext()) {
                stringBuffer.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            Gson g = new Gson();
            pokemon = g.fromJson(stringBuffer.toString(), PokemonBean.class);
            pokemonRepository.save(pokemon);
        }
        return pokemon;
    }

    @Override
    public void savePokemonIndex(PokemonIndexTO pokemonIndexTO) {
        PokemonBean bean = new PokemonBean();
        bean.setUrl(pokemonIndexTO.getUrl());
        bean.setName(pokemonIndexTO.getName());
        bean.setId(setIdPokemonBean(pokemonIndexTO.getUrl()));
        pokemonRepository.save(bean);
    }

    private int setIdPokemonBean(String url){
        url = StringUtils.chop(url);
        String baseUrl = "https://pokeapi.co/api/v2/pokemon/";
        return Integer.valueOf(url.substring(baseUrl.length()));
    }

}