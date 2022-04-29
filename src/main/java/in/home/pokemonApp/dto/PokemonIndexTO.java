package in.home.pokemonApp.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonIndexTO {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
}