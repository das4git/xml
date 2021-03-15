import db.DB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import service.CsvBuilder;
import service.XmlBuilder;
import service.XmlTransformer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    private static final Logger log = LogManager.getLogger("Main");
    private final static String firstResultXml = "XML/src/resources/firstResult.xml";
    private final static String temporaryXml = "XML/src/resources/temporary.xml";
    private final static String xsltTemplate = "XML/src/resources/xsltTemplate.xslt";
    private final static String finalResultXml = "XML/src/resources/finalResult.xml";
    private final static String csvTemplate = "XML/src/resources/csvTemplate.xsl";
    private final static String resultCsv = "XML/src/resources/result.csv";

    public static void main(String[] args) throws SQLException {
        //creating DB
        DB db = new DB();
        db.createDB();
        db.insertFields(500);
        log.info("Created DB");

        //creating first xml file
        XmlBuilder xmlBuilder = new XmlBuilder(db);
        try {
            xmlBuilder.createXmlFile(firstResultXml, temporaryXml);
            log.info("Created firstResultXml");
        } catch (IOException e) {
            log.error("Caught" + e);
        } catch (XMLStreamException e) {
            log.error("Caught" + e);
        } catch (TransformerException e) {
            log.error("Caught" + e);
        }

        //xslt transform of first xml file
        XmlTransformer xmlTransformer = new XmlTransformer();
        try {
            xmlTransformer.transformXml(firstResultXml,
                    xsltTemplate,
                    finalResultXml);
            log.info("XSLT transforming done");
        } catch (TransformerException e) {
            log.error("Caught" + e);
        }


        // creating csv file
        CsvBuilder csvBuilder = new CsvBuilder();

        try {
            csvBuilder.transformXmlToCsv(
                    finalResultXml,
                    resultCsv,
                    csvTemplate);
            log.info("Transformed XML to CSV");
        } catch (TransformerException e) {
            log.error("Caught" + e);
        } catch (IOException e) {
            log.error("Caught" + e);
        } catch (SAXException e) {
            log.error("Caught" + e);
        } catch (ParserConfigurationException e) {
            log.error("Caught" + e);
        }

        db.closeDB();
        log.info("Closed DB");

    }
}
