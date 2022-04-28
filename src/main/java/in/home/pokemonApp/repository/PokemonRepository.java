package in.home.pokemonApp.repository;

import in.home.pokemonApp.bean.PokemonBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokemonRepository extends JpaRepository<PokemonBean, Integer> {

    @Query(value = "SELECT * FROM pokemon ORDER BY weight DESC LIMIT 5", nativeQuery = true)
    List<PokemonBean> pokemonByWeight();

    @Query(value = "SELECT * FROM pokemon ORDER BY height DESC LIMIT 5", nativeQuery = true)
    List<PokemonBean> pokemonByHeight();

    @Query(value = "SELECT * FROM pokemon ORDER BY base_experience DESC LIMIT 5", nativeQuery = true)
    List<PokemonBean> pokemonByBaseExperience();
}