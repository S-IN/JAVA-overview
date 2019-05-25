// ==========================================================================
// Classe ErreurDtd                                      Application ArbreXml
// ==========================================================================

import org.xml.sax.*;

// ==========================================================================
// Classe de gestion des erreurs liees a la verification DTD
// ==========================================================================
public class ErreurDtd implements ErrorHandler
{
    public void warning(SAXParseException exception) throws SAXException
    {
        throw new SAXException("Warning : " + exception.getMessage());
    }

    public void error(SAXParseException exception) throws SAXException
    {
        throw new SAXException("Error : " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException
    {
        throw new SAXException("Fatal error : " + exception.getMessage());
    }
}
