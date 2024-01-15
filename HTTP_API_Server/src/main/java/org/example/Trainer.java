package org.example;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Trainer {
    @JsonProperty("name")
    private String name;
    @JsonProperty("prenom")
    private String firstname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("birthdate")
    private Date birthdate;
    @JsonProperty("team")
    private List<Pokemon> team;

    public Trainer() { }

    public Trainer(String name, String firstname, Date birthdate, List<Pokemon> team) {
        this.name = name;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.team = team;
    }

}
