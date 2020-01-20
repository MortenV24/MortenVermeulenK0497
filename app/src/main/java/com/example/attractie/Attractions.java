package com.example.attractie;

public class Attractions {
    private String naam;
    private String plaats;
    private String img;

    // Constructor
    public Attractions(String naam, String plaats, String img) {
        this.naam = naam;
        this.plaats = plaats;
        this.img = img;
    }


    // Getters & Setters
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Attractions()
    {

    }

}
