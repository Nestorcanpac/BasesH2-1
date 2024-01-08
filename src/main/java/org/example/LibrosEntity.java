package org.example;

import java.util.Objects;

public class LibrosEntity {
    private int id;
    private String titol;
    private String autor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibrosEntity that = (LibrosEntity) o;
        return id == that.id && Objects.equals(titol, that.titol) && Objects.equals(autor, that.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titol, autor);
    }
}
