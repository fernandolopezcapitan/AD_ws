package com.ad.javier.ejercicio1xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Created by Javier on 22/11/2015.
 */
@ElementList
public class Humedad {

    @Attribute
    private int humMax;

    @Attribute
    private int humMin;

}
