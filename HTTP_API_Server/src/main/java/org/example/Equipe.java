package org.example;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipe {
    @JsonProperty("nomEquipe")
    private String nomEquipe;
    @JsonManagedReference
    @JsonProperty("membres")
    private List<Pokemon> membres;

    // Exemple de constructeur
    public Equipe(String nomEquipe, List<Pokemon> membres) {
        this.nomEquipe = nomEquipe;
        this.membres = membres;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public List<Pokemon> getMembres() {
        return membres;
    }

    public void setNomEquipe(String nomEquipe){
        this.nomEquipe = nomEquipe;
    }

    public void supprimerTousPokemons() {
        this.membres.clear();
    }

    public void ajouterPokemon(Pokemon pokemon) {
        this.membres.add(pokemon);
    }

    // Autres méthodes et fonctionnalités
}