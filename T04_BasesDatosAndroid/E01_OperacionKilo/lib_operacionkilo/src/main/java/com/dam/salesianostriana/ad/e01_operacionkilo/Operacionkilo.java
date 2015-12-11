package com.dam.salesianostriana.ad.e01_operacionkilo;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Operacionkilo {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "de.greenrobot.daoexample");
        crearTablas(schema);
        new DaoGenerator().generateAll(schema, "../app/src/main/java/com/dam/salesianostriana/ad/e01_operacionkilo/greendao");
    }

    private static void crearTablas(Schema schema) {

        // Tabla Cajas
        Entity cajas = schema.addEntity("Cajas");
        cajas.addIdProperty();
        cajas.addIntProperty("numero").notNull();

        // Tabla Alimentos ....
        // Entity alimentos =
    }




}
