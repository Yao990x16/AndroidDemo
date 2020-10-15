package pres.yao.shiyan2;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;

/**
 * @ClassName JinShanXMLparser
 * @Description TOOD
 * Date 2020/10/15 11:09
 **/
public class JinShanXMLparser {
    public SAXParserFactory factory=null;
    public XMLReader reader=null;

    public JinShanXMLparser(){

        try {
            factory=SAXParserFactory.newInstance();
            reader=factory.newSAXParser().getXMLReader();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void parseJinShanXml(DefaultHandler content, InputSource inSource){
        if(inSource==null)
            return;
        try {
            reader.setContentHandler(content);
            reader.parse(inSource);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
