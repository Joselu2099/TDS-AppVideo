package model;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Label implements Comparable<Label> {
//    DIBUJOS_ANIMADOS, PELICULA, SERIE, INTRIGA, TERROR, CLASICO, VIDEOCLIP, ADULTOS, INFANTIL

    public final String name;

    public static final Label DIBUJOS_ANIMASDOS = new Label("DIBUJOS_ANIMADOS");
    public static final Label PELICULA= new Label("PELICULA");
    public static final Label SERIE = new Label("SERIE");
    public static final Label INTRIGA = new Label("SERIE");
    public static final Label TERROR = new Label("TERROR");
    public static final Label CLASICO = new Label("CLASICO");
    public static final Label VIDEOCLIP = new Label("VIDEOCLIP");
    public static final Label ADULTOS = new Label("ADULTOS");
    public static final Label INFANTIL = new Label("INFANTIL");

    private static Set<Label> existingLabelSet;

    private Label(String name) {
        this.name = name.toUpperCase(Locale.ROOT);
        if (existingLabelSet == null)
            existingLabelSet = new HashSet<>();
        existingLabelSet.add(this);
    }

    public static Set<Label> getExistingLabelSet(){
        return Collections.unmodifiableSet(existingLabelSet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Label)) return false;

        Label label = (Label) o;

        return name != null ? name.toUpperCase(Locale.ROOT).equals(label.name.toUpperCase(Locale.ROOT)) : label.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.toUpperCase(Locale.ROOT).hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }

    public String name(){
        return name;
    }
//    public static String name(Label l){
//        return l.name;
//    }

    // Factoria
    public static Label valueOf(String name){
        if (name == null || name.equals(""))
            return null;
        return new Label(name);
    }

    @Override
    public int compareTo(@NotNull Label o) {
        return this.name.compareTo(o.name);
    }
}
