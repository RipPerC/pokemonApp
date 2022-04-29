package in.home.pokemonApp;

import in.home.pokemonApp.bean.PokemonBean;
import in.home.pokemonApp.controller.PokemonDataController;
import in.home.pokemonApp.dto.DataTO;
import in.home.pokemonApp.dto.PokemonDTO;
import in.home.pokemonApp.dto.PokemonIndexTO;
import in.home.pokemonApp.repository.PokemonRepository;
import in.home.pokemonApp.service.PokeApiService;
import in.home.pokemonApp.service.PokemonService;
import in.home.pokemonApp.service.impl.PokeApiServiceImpl;
import in.home.pokemonApp.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PokemonDataControllerTest {

    @Autowired
    private PokemonDataController pokemonDataController;
    @Autowired
    private PokeApiService pokeApiServiceMock;
    @Autowired
    private PokemonService pokemonServiceMock;
    @Mock
    private PokemonRepository pokemonRepositoryMock;

    @TestConfiguration
    public static class TestConfig {

        @Bean
        @Primary
        public PokeApiServiceImpl pokeApiServiceMock() {
            return mock(PokeApiServiceImpl.class);
        }

        @Bean
        @Primary
        public PokemonServiceImpl pokemonServiceMock() {
            return mock(PokemonServiceImpl.class);
        }
    }

    @Test
    void completeData() throws IOException {
        List<PokemonIndexTO> pokemonIndexTOList = new ArrayList<>();
        DataTO data = new DataTO();
        data.setCount(1);
        PokemonIndexTO pokemonIndexTO = new PokemonIndexTO("poke", "url");
        pokemonIndexTOList.add(pokemonIndexTO);
        data.setPokemonIndexList(pokemonIndexTOList);

        when(pokeApiServiceMock.getPokemonDataList()).thenReturn(data);
        when(pokeApiServiceMock.getPokemonData(anyString())).thenReturn(new PokemonBean());
        ResponseEntity<String> response = pokemonDataController.completeData();
        assertNotNull(response);
    }

    @Test
    void completeDataError(){
        try {
            when(pokeApiServiceMock.getPokemonDataList()).thenThrow(new IOException("Error"));
            ResponseEntity<String> response = pokemonDataController.completeData();
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Error"));
        }
    }

    @Test
    void testHeviestPokemon(){
        String by = "w";
        List<PokemonBean> list = new ArrayList<>();
        PokemonBean poke = new PokemonBean();
        poke.setId(1);
        poke.setName("poke");
        list.add(poke);

        when(pokemonRepositoryMock.pokemonByWeight()).thenReturn(list);
        ResponseEntity<List<PokemonDTO>> response = pokemonDataController.orderBy(by);
        assertNotNull(response);
    }

    @Test
    void testHighestPokemon(){
        String by = "h";
        List<PokemonBean> list = new ArrayList<>();
        PokemonBean poke = new PokemonBean();
        poke.setId(1);
        poke.setName("poke");
        list.add(poke);

        when(pokemonRepositoryMock.pokemonByHeight()).thenReturn(list);
        ResponseEntity<List<PokemonDTO>> response = pokemonDataController.orderBy(by);
        assertNotNull(response);
    }

    @Test
    void testBaseExperiencePokemon(){
        String by = "e";
        List<PokemonBean> list = new ArrayList<>();
        PokemonBean poke = new PokemonBean();
        poke.setId(1);
        poke.setName("poke");
        list.add(poke);

        when(pokemonRepositoryMock.pokemonByBaseExperience()).thenReturn(list);
        ResponseEntity<List<PokemonDTO>> response = pokemonDataController.orderBy(by);
        assertNotNull(response);
    }

    @Test
    void testNoOrder(){
        String by = "fail";

        ResponseEntity<List<PokemonDTO>> response = pokemonDataController.orderBy(by);
        assertNotNull(response);
        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
    }
}
