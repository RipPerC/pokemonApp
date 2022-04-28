package in.home.pokemonApp.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataTO {

    @SerializedName("count")
    private Integer count;
    @SerializedName("results")
    private List<PokemonIndexTO> pokemonIndexList;
}
