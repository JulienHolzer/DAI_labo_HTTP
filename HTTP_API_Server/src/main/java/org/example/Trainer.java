package org.example;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Trainer {
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("naissance")
    private Date naissance;
    @JsonProperty("equipe")
    private List<Pokemon> equipe;

    public Trainer() { }

    public Trainer(String nom, String prenom, Date naissance, List<Pokemon> equipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.equipe = equipe;
    }

}
