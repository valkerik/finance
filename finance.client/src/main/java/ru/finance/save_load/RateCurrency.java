package ru.finance.save_load;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.finance.entity.Currency;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RateCurrency {


    public static HashMap<String, Double> getRatesFromNet(Currency base) throws Exception {
        HashMap<String, NodeList> result = new HashMap<>();

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + sd.format(new Date());
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        Document doc = builderFactory.newDocumentBuilder().parse(new URL(url).openStream());

        NodeList list = doc.getElementsByTagName("Valute");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            NodeList nodeChildNodes = node.getChildNodes();
            for (int j = 0; j < nodeChildNodes.getLength(); j++) {
                if(nodeChildNodes.item(j).getNodeName().equals("CharCode"))
                result.put(nodeChildNodes.item(j).getTextContent(), nodeChildNodes);
            }
        }

        HashMap<String, Double> rates = new HashMap<>();
        for (Map.Entry<String, NodeList> entry : result.entrySet()) {
            NodeList temp = entry.getValue();
            double value = 0;
            int nominal = 0;
            for (int i = 0; i < temp.getLength(); i++) {
                if(temp.item(i).getNodeName().equals("Value")){
                    value = Double.parseDouble(temp.item(i).getTextContent().replace(',', '.'));
                }else if(temp.item(i).getNodeName().equals("Nominal")){
                    nominal = Integer.parseInt(temp.item(i).getTextContent());
                }
            }
            double amount = value / nominal;
            rates.put(entry.getKey(),(double)(Math.round(amount * 10000 ) /10000));
        }

        rates.put("RUB", 1.0);
        double div= rates.get(base.getCode());
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            entry.setValue(entry.getValue() / div);
        }
        return rates;
    }
}
