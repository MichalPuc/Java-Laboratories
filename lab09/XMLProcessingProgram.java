import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class XMLProcessingProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // Wczytanie danych XML przy użyciu JAXB
        System.out.println("Wczytywanie danych XML przy użyciu JAXB");
        System.out.print("Podaj ścieżkę do pliku XML: ");
        String xmlFilePath = scanner.nextLine();
        try {
            File xmlFile = new File(xmlFilePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<Root> jaxbElement = unmarshaller.unmarshal(new StreamSource(xmlFile), Root.class);
            Root root = jaxbElement.getValue();
            RootData rootData = root.getData().getRootData();
            System.out.println(rootData);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // Wczytanie danych XML przy użyciu JAXP (SAX i DOM)
        System.out.println("\nWczytywanie danych XML przy użyciu JAXP");
        System.out.print("Podaj ścieżkę do pliku XML: ");
        String xmlFilePath2 = scanner.nextLine();
        try {
            File xmlFile = new File(xmlFilePath2);
            System.out.println("Parsowanie przy użyciu SAX");
            // Parsowanie przy użyciu SAX
            SAXParserFactory saxfactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxfactory.newSAXParser();
            MyHandler dh = new MyHandler();
            saxParser.parse(xmlFile,dh);


            System.out.println("Parsowanie przy użyciu DOM");
            // Parsowanie przy użyciu DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            System.out.println(document.getDocumentElement().getTextContent());
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        // Przetwarzanie danych XML przy użyciu arkusza transformacji XSLT
        System.out.println("\nPrzetwarzanie danych XML przy użyciu arkusza XSLT");
        System.out.print("Podaj ścieżkę do pliku XML: ");
        String xmlFilePath3 = scanner.nextLine();
        System.out.print("Podaj ścieżkę do arkusza XSLT: ");
        String xsltFilePath = scanner.nextLine();
        try {
            File xmlFile = new File(xmlFilePath3);
            File xsltFile = new File(xsltFilePath);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource xsltSource = new StreamSource(xsltFile);
            Transformer transformer = transformerFactory.newTransformer(xsltSource);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            DOMSource xmlSource = new DOMSource(document);

            StreamResult output = new StreamResult(System.out); // Wyjście na ekran
            transformer.transform(xmlSource, output);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
