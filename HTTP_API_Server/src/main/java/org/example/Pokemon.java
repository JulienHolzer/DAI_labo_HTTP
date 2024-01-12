package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pokemon {
    @JsonProperty("pokedexnumber")
    private int pokedexnumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("weight")
    private double weight;
    @JsonProperty("size")
    private double size;
    @JsonProperty("type1")
    Type type1;
    @JsonProperty("type2")
    Type type2;


    public Pokemon() {

    }

    public Pokemon(int pokedexnumber, String name, double weight, double size, Type type1) {
        this.pokedexnumber = pokedexnumber;
        this.name = name;
        this.weight = weight;
        this.size = size;
        this.type1 = type1;
        this.type2 = null;
    }

    public Pokemon(int pokedexnumber, String name, double weight, double size, Type type1, Type type2) {
        this.pokedexnumber = pokedexnumber;
        this.name = name;
        this.weight = weight;
        this.size = size;
        this.type1 = type1;
        this.type2 = type2;
    }


}
