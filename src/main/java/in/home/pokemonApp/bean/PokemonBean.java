package in.home.pokemonApp.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pokemon")
public class PokemonBean {

    @Id
    @Column
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "url")
    private String url;
    @Column (name = "base_experience")
    private long base_experience;
    @Column (name = "height")
    private int height; //Decímetros
    @Column (name = "weight")
    private int weight; //Hectogramos

}
