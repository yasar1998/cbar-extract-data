package org.example;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static HttpURLConnection connectToServer(String url) throws IOException {

        URL serverUrl = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();

        connection.setRequestMethod("GET");

        return connection;
    }


    public static ArrayList<Currency> parseXMLtoList(HttpURLConnection connection) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(connection.getInputStream());
        Element rootElement = document.getRootElement();

        ArrayList<Currency> currencies = new ArrayList<>();
        String code;
        String nominal = null;
        String name = null;
        String value = null;

        for(Element i : rootElement.getChildren()){
            for(Element j : i.getChildren()){
                code = j.getAttribute("Code").getValue();
                for(Element k : j.getChildren()){
                    if(k.getName().equals("Nominal")){
                        nominal = k.getValue();
                    }
                    if(k.getName().equals("Name")){
                        name = k.getValue();
                    }
                    if(k.getName().equals("Value")){
                        value = k.getValue();
                    }
                }
                Currency c = new Currency(code, nominal, name, Double.valueOf(value));
                currencies.add(c);
            }
        }
        for(Currency c : currencies){
            System.out.println(c);
        }

        return currencies;
    }

    public static void main(String[] args) {
        try {
            String url = "https://cbar.az/currencies/15.03.2024.xml";

            HttpURLConnection connection = connectToServer(url);

            List<Currency> currencies = parseXMLtoList(connection);

            for(Currency c : currencies){
                System.out.println(c);
            }
        }
        catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
    }
}