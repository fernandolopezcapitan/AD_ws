package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class NoteGreenDaoGenerator {

    public static void main(String[] args) {

        // A Schema hay q pasarle la version y la ruta del paquete
        Schema schema = new Schema(1000, "de.greenrobot.daoexample");
        addNote(schema);
        try {

            if (!Files.isDirectory(Paths.get( "./src-gen")))
                Files.createDirectory(Paths.get("./src-gen"));

            new DaoGenerator().generateAll(schema, "./src-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Añadirle las entidades
    // Schema tiene un metodo addEntity q le pasas la clase q quieres (en este caso "Note")
    // ver
    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }





}


