package com.nataliasep.toolbarcontainer;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PaisParser {
    private Pais[] paises;
    private final InputStream paisesFile;

    public PaisParser(MainActivity c){
        this.paisesFile = c.getResources().openRawResource(R.raw.countries);
    }

    public boolean parse(){
        boolean parsed = false;
        paises = null;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(paisesFile);
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("country");
            paises = new Pais[items.getLength()];
            for(int i = 0; i< items.getLength(); i++){
                Node item = items.item(i);
                String codigoPais = item.getAttributes().getNamedItem("countryCode").getNodeValue();
                String nombrePais = item.getAttributes().getNamedItem("countryName").getNodeValue();
                String capital = item.getAttributes().getNamedItem("capital").getNodeValue();
                long poblacion = Long.parseLong(item.getAttributes().getNamedItem("population").getNodeValue());
                paises[i] = new Pais(codigoPais, nombrePais, poblacion, capital);

            }
            parsed = true;

        }catch(ParserConfigurationException pce) {
            Log.e("PaisParser", "ParserConfigurationException: "+pce.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("PaisParser", "Unknown Exception: "+e.getLocalizedMessage());
        }
        return parsed;

    }
    public Pais[] getPaises() {

        return this.paises;
    }

}
