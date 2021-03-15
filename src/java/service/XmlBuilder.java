package service;

import db.DB;
import model.Article;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class XmlBuilder {
    private DB db;

    public XmlBuilder(DB db) {
        this.db = db;
    }

    public void createXmlFile(String resultXml, String temporaryXml)
            throws IOException, XMLStreamException, TransformerException, SQLException {

        List<Article> dataFromDb = db.getArticles();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(
                new FileWriter(temporaryXml));

        writer.writeStartDocument("UTF8", "1.0");
        writer.writeStartElement("articles");
        for (Article article: dataFromDb) {
            writer.writeStartElement("article");
            writer.writeAttribute("id_art", String.valueOf(article.getId_art()));
            writer.writeAttribute("name", article.getName());
            writer.writeAttribute("code", String.valueOf(article.getCode()));
            writer.writeAttribute("username", article.getUserName());
            writer.writeAttribute("guid", article.getGuid());
            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.close();

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(
                new StreamSource(new BufferedInputStream(new FileInputStream(temporaryXml))),
                new StreamResult(new FileOutputStream(resultXml)));
    }
}
