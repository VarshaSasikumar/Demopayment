package utils;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
public class XmlParser {

    public boolean isValuePresentInXML(String xml, String xpathExpression, String expectedValue) {
        try {
            // Parse the XML string into a Document
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xml));
            Document document = builder.parse(inputSource);

            // Create an XPath object
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Compile the XPath expression
            XPathExpression expression = xPath.compile(xpathExpression);

            // Evaluate the XPath expression on the XML document
            String actualValue = (String) expression.evaluate(document, XPathConstants.STRING);

            // Compare the actual value with the expected value
            return actualValue.equals(expectedValue);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
