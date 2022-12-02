package com.jordivicent.linktracker.Model;

import java.util.List;

public class WebPage {
    String nombreWeb;
    String urlWeb;
    List<String> enlaces;

    public WebPage(String nombreWeb, String urlWeb) {
        this.nombreWeb = nombreWeb;
        this.urlWeb = urlWeb;
    }

    public String getNombreWeb() {
        return nombreWeb;
    }

    public WebPage setNombreWeb(String nombreWeb) {
        this.nombreWeb = nombreWeb;
        return this;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public WebPage setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
        return this;
    }

    public List<String> getEnlaces() {
        return enlaces;
    }

    public WebPage setEnlaces(List<String> enlaces) {
        this.enlaces = enlaces;
        return this;
    }
}
