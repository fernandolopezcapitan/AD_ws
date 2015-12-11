package com.salesianostriana.ad;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGeneratorTask {
    public static void main (String[] args) throws Exception{

        //Creamos el esquema
        Schema schema = new Schema(1000, "de.greenrobot.daoexample");

        //Añadimos la entidad (la tabla)
        // Por cada tabla, una Entity distinta
        Entity note = schema.addEntity("Note");

        //Añadimos las propiedades
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");


        new DaoGenerator().generateAll(schema, "../DaoExample/src-gen");


    }
}
