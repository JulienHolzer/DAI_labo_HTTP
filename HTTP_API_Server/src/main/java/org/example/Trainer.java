package org.example;

import java.util.Date;

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
    @JsonManagedReference
    @JsonProperty("equipe")
    private Equipe equipe;

    public Trainer() { }

    public Trainer(String nom, String prenom, Date naissance, Equipe equipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.equipe = equipe;
    }

}
