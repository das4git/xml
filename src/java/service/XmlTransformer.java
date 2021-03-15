package service;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XmlTransformer {

    public void transformXml(String sourceFile, String xsltTemplate, String resultFile)
            throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.
                newTransformer(new StreamSource(new File(xsltTemplate)));
        StreamSource source = new StreamSource(new File(sourceFile));
        StreamResult result = new StreamResult(new File(resultFile));
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
        transformer.transform(source, result);
    }
}
