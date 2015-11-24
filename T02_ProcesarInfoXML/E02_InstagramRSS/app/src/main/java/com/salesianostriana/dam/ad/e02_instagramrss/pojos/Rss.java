package com.salesianostriana.dam.ad.e02_instagramrss.pojos;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by flopez on 24/11/2015.
 */
@Root(name = "rss", strict = false)
public class Rss {

    @Element(name = "channel")
    private Channel channel;

    public Rss(){}

    public Rss(Channel channel){
        this.channel = channel;
    }

    public Channel getChannel(){
        return this.channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Rss{" +
                "channel=" + channel +
                '}';
    }
}
