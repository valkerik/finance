package ru.finance.xmlelement;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.finance.save_load.SaveData;
import ru.finance.util.Settings;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveLoadXML {



    public static void save(SaveData saveData){
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Wrapper wrapper = new Wrapper();
            wrapper.setAccounts(saveData.getAccounts());
            wrapper.setArticles(saveData.getArticles());
            wrapper.setTransactions(saveData.getTransactions());
            wrapper.setTransfers(saveData.getTransfers());
            wrapper.setCurrencies(saveData.getCurrencies());

            marshaller.marshal(wrapper, Settings.getFileSave());
        } catch (JAXBException e) {
            Logger.getLogger(SaveLoadXML.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }

    public static void load(SaveData saveData){
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Wrapper wrapper =  (Wrapper) unmarshaller.unmarshal(Settings.getFileSave());
            saveData.setAccounts(wrapper.getAccounts());
            saveData.setArticles(wrapper.getArticleList());
            saveData.setTransactions(wrapper.getTransactions());
            saveData.setTransfers(wrapper.getTransfers());
            saveData.setCurrencies(wrapper.getCurrencies());


        } catch (JAXBException e) {
            Logger.getLogger(SaveLoadXML.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
    }
}
