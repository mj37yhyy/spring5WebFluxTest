package com.example.spring5WebFluxTest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scope {

    String Type;
    String Name;
    String Action;

    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @JsonProperty("Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JsonProperty("Action")
    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}
