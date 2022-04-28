package in.home.pokemonApp.controller;

import in.home.pokemonApp.dto.DataTO;
import in.home.pokemonApp.dto.PokemonDTO;
import in.home.pokemonApp.dto.PokemonIndexTO;
import in.home.pokemonApp.service.PokeApiService;
import in.home.pokemonApp.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonDataController {

    private Logger log = LoggerFactory.getLogger(PokemonDataController.class);
    private PokeApiService pokeApiService;
    private PokemonService pokemonService;

    public PokemonDataController(PokeApiService pokeApiService, PokemonService pokemonService) {
        this.pokeApiService = pokeApiService;
        this.pokemonService = pokemonService;
    }

    /**
     * Se realiza la recarga de toda la información obtenida y se guarda en la base de datos H2
     * @return String
     */
    @GetMapping()
    public ResponseEntity<String> completeData() {
        StopWatch watch = new StopWatch();
        StringBuffer notifyGate = new StringBuffer();
        notifyGate.append("Realizada la precarga de información.\nSeleccione la operación que desea realizar:\n");
        notifyGate.append("\"http://localhost:8080/api/v1/pokemon/order?by={w|h|e}\"\n");
        notifyGate.append("Tiempo transcurrido (en segundos): ");

        log.info("BEGIN completeData");
        watch.start();

        try {
            DataTO data = pokeApiService.getPokemonDataList();

            for (PokemonIndexTO index : data.getPokemonIndexList()) {
                pokeApiService.getPokemonData(index.getUrl());
            }
        } catch (IOException e) {
            log.error("Error al obtener el listado de Pokemons : " + e.getLocalizedMessage());
            return new ResponseEntity<>("Error al obtener el listado de Pokemons : " + e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("END completeData");
        watch.stop();
        return new ResponseEntity<>(notifyGate.toString() + watch.getTotalTimeSeconds(), HttpStatus.OK);
    }

    /**
     * Se obtienen los 5 pokemons con más peso/altura/experiencia base.
     * @param by sistema de ordenación w-peso/h-altura/e-experiencia
     * @return Listado con los 5 pokemons correspondientes
     */
    @GetMapping("/order")
    public ResponseEntity<List<PokemonDTO>> orderBy (@RequestParam (required = true) String by) {
        log.info ("BEGIN orderBy = {}", by);
        List<PokemonDTO> pokemonList = new ArrayList<>();

        if(by.equals("w")) {
            pokemonList = pokemonService.getHeaviestPokemon();
        } else if (by.equals("h")) {
            pokemonList = pokemonService.getHighestPokemon();
        } else {
            pokemonList = pokemonService.getMoreBaseExperiencePokemon();
        }
        return new ResponseEntity<List<PokemonDTO>>(pokemonList, HttpStatus.OK);
    }
}