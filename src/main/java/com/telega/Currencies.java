package com.telega;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Currencies {
    public static String getCurrency(String value) throws Exception {
        URL url = new URL("https://www.cbr-xml-daily.ru/daily_utf8.xml");
        Scanner in = new Scanner((InputStream) url.getContent());

        String result = "";
        while (in.hasNext()){
            result+=in.nextLine();
        }
        //JSONObject jsonObject = new JSXMLONObject(result);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new ByteArrayInputStream(result.getBytes()));

        NodeList nodeList = document.getElementsByTagName("Value");

        return value.equals("доллар")?nodeList.item(10).getTextContent():nodeList.item(11).getTextContent();
    }

    public static Model getModel() throws Exception{
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Кувандык&&units=metric&&appid=ada41553a2ad1f904423c8b287507d37");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()){
            result+=in.nextLine();
        }

        JSONObject jsonObject = new JSONObject(result);

        JSONObject main = jsonObject.getJSONObject("main");
        Model model = new Model();
        double temp = (double) main.get("temp");

        model.setTemp(Double.toString(temp));
        return model;
    }
    public static String getJson() throws Exception {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Кувандык&&units=metric&&appid=ada41553a2ad1f904423c8b287507d37");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.toString();
    }


}
