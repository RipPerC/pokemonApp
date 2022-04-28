package in.home.pokemonApp.service.impl;

import in.home.pokemonApp.bean.PokemonBean;
import in.home.pokemonApp.dto.PokemonDTO;
import in.home.pokemonApp.repository.PokemonRepository;
import in.home.pokemonApp.service.PokemonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    private ModelMapper modelMapper;

    public PokemonServiceImpl(PokemonRepository pokemonRepository, ModelMapper modelMapper) {
        this.pokemonRepository = pokemonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PokemonDTO> getHeaviestPokemon() {
        List<PokemonBean> pokemonBeanList = pokemonRepository.pokemonByWeight();
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();

        for (PokemonBean pb: pokemonBeanList) {
            PokemonDTO pd = modelMapper.map(pb, PokemonDTO.class);
            pokemonDTOList.add(pd);
        }

        return pokemonDTOList;
    }

    @Override
    public List<PokemonDTO> getHighestPokemon() {
        List<PokemonBean> pokemonBeanList = pokemonRepository.pokemonByHeight();
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();

        for (PokemonBean pb: pokemonBeanList) {
            PokemonDTO pd = modelMapper.map(pb, PokemonDTO.class);
            pokemonDTOList.add(pd);
        }

        return pokemonDTOList;
    }

    @Override
    public List<PokemonDTO> getMoreBaseExperiencePokemon() {
        List<PokemonBean> pokemonBeanList = pokemonRepository.pokemonByBaseExperience();
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();

        for (PokemonBean pb: pokemonBeanList) {
            PokemonDTO pd = modelMapper.map(pb, PokemonDTO.class);
            pokemonDTOList.add(pd);
        }

        return pokemonDTOList;
    }
}