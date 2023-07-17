import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class MyHandler extends DefaultHandler {
    private StringBuilder content;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        content = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (content != null) {
            content.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (content != null) {
            String elementContent = content.toString().trim();
            if (!elementContent.isEmpty()) {
                System.out.println(qName + ": " + elementContent);
            }
            content = null;
        }
    }
}
