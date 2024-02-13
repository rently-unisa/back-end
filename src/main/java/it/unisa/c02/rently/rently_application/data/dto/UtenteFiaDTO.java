package it.unisa.c02.rently.rently_application.data.dto;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;


@Getter
@Setter
public class UtenteFiaDTO {

    @JsonProperty("Gender")
    private int Gender;

    @JsonProperty("Married")
    private int Married;

    @JsonProperty("Age")
    private int Age;

    @JsonProperty("Graduated")
    private int Graduated;

    @JsonProperty("Profession")
    private int Profession;

    @JsonProperty("Budget")
    private int Budget;

    @JsonProperty("Family_Size")
    private int Family_Size;
}
