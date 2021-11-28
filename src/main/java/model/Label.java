package model;

import java.util.HashMap;
import java.util.Locale;

public class Label {
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

    private Label(String name) {
        this.name = name;
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

    public static Label valueOf(String name){
        return new Label(name);
    }

}
