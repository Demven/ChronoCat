package com.demven.chronocat.data.beans;

/**
 * Class that describes a category of a work (act, doing)
 * @author Dmitry Salnikov (Demven)
 * @since 17.03.2014
 */
public class Category {

    private Long id;
    private String name;
    private int color;

    public Category(Long id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
