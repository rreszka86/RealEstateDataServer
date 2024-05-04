package pl.pollub.integracja_projekt.Utils.XmlReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlReader {
    private Document document;
    public XmlReader(String filepath){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filepath));
            document.getDocumentElement().normalize();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<InterestRatesDTO> parseXML(){
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        List<InterestRatesDTO> ratesList = new ArrayList<>();

        NodeList nList = document.getElementsByTagName("pozycje");
        for(int i = 0; i < nList.getLength(); i++){
            Node node = nList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                InterestRatesDTO rates = new InterestRatesDTO();
                rates.setEffectiveFrom(eElement.getAttribute("obowiazuje_od"));
                NodeList nList2 = eElement.getElementsByTagName("pozycja");
                List<EntryDTO> entryList = new ArrayList<>();
                for(int j = 0; j < nList2.getLength(); j++) {
                    Node node2 = nList2.item(j);
                    if(node2.getNodeType() == Node.ELEMENT_NODE){
                        Element eElement2 = (Element) node2;
                        EntryDTO entry = new EntryDTO();
                        entry.setId(eElement2.getAttribute("id"));
                        entry.setInterestPercentage(eElement2.getAttribute("oprocentowanie"));
                        entry.show();
                        entryList.add(entry);
                    }
                }
                rates.setEntryList(entryList);
                ratesList.add(rates);
            }
        }
        return ratesList;
    }
}