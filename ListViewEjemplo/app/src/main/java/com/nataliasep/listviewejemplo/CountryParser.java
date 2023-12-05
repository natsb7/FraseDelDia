package com.nataliasep.listviewejemplo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CountryParser {

    public static List<Country> parseCountriesFromXML(String xmlFilePath) {
        List<Country> countryList = new ArrayList<>();

        try {
            File xmlFile = new File(xmlFilePath);

            // Crear un constructor de documentos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Obtener la lista de elementos "country" del documento
            NodeList nodeList = doc.getElementsByTagName("country");

            // Iterar a través de los elementos "country" y crear objetos Country
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element countryElement = (Element) nodeList.item(i);
                String countryName = countryElement.getAttribute("name");
                String countryCapital = countryElement.getAttribute("capital");
                long population = Integer.parseInt(countryElement.getAttribute("population"));
                String countryCode = countryElement.getAttribute("flagResource");
                String countryIA3 = countryElement.getAttribute("isoAlpha3");


                // Crear un objeto Country y agregarlo a la lista
                Country country = new Country(countryCode, countryName, population,countryCapital, countryCode);
                countryList.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countryList;
    }
}

