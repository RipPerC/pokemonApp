package in.home.pokemonApp.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PokemonDTO {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("base_experience")
    private long base_experience;
    @SerializedName("height")
    private int height; //Decímetros
    @SerializedName("weight")
    private int weight; //Hectogramos
}
