package com.dam.salesianostriana.ad.e01_operacionkilo;

import java.nio.file.Files;
import java.nio.file.Paths;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Operacionkilo {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "de.greenrobot.daoexample");
        crearTablas(schema);

        try {

            if (!Files.isDirectory(Paths.get("./src-gen")))
                Files.createDirectory(Paths.get("./src-gen"));
            new DaoGenerator().generateAll(schema, "./src-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void crearTablas(Schema schema) {

        // Tabla Alimentos
        Entity alimentos = schema.addEntity("Alimentos");
        alimentos.addIdProperty();
        alimentos.addStringProperty("nombre");

        // Tabla Cajas
        Entity cajas = schema.addEntity("Cajas");
        cajas.addIdProperty();
        cajas.addIntProperty("numero").notNull();

        // Tabla enlace de alimentos a cajas (N:M)
        // Contiene las claves primarias de ambas tablas
        Entity alimentos_cajas = schema.addEntity("AlimCaja");
        alimentos_cajas.addIntProperty("cantidad");

        Property idAlimentos = alimentos_cajas.addLongProperty("id_alimentos").notNull().getProperty();
        Property idCajas = alimentos_cajas.addLongProperty("id_cajas").notNull().getProperty();

        alimentos.addToMany(alimentos_cajas,idAlimentos);
        cajas.addToOne(alimentos_cajas,idCajas);
    }
}
