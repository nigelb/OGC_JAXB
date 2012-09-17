package net.opengis.sos.v_1_0_0;

import junit.framework.Assert;
import net.opengis.swe.v_1_0_1.DataArrayPropertyType;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;


public class TestSOSParsing {


    @Test
    public void parseSosInsertObservation2() throws JAXBException {

        InsertObservation ins = (InsertObservation) createContext().createUnmarshaller().unmarshal(new File(System.getProperty("test.data.root"),"sos/1.0.0/schema/src/test/samples/sosInsertObservation2.xml"));
        Assert.assertEquals(((DataArrayPropertyType) ins.getObservation().getResult()).getDataArray().getValues().getAny().size(), 1);

    }

    private JAXBContext createContext() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Enumeration<URL> urls = TestSOSParsing.class.getClassLoader().getResources("META-INF/sun-jaxb.episode");
            InputStream is;
            final ArrayList<String> classes = new ArrayList<String>();
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                saxParser.parse(is = url.openStream(), new DefaultHandler() {
                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        if (qName.equalsIgnoreCase("class")) {
                            classes.add(attributes.getValue("ref"));
                        }
                    }
                });
                is.close();
            }
            ArrayList<String> clz = new ArrayList<String>();
            for (String aClass : classes) {
                try {
                    String pkg = getPackage(Class.forName(aClass).getPackage().getName());

                    if (pkg != null && !clz.contains(pkg)) {
                        clz.add(pkg);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            StringBuilder buf = new StringBuilder();
            String del = "";
            for (String s : clz) {
                buf.append(del).append(s);
                del = ":";
            }
            System.setProperty("com.sun.xml.bind.v2.runtime.JAXBContextImpl.fastBoot", "true");
            return JAXBContext.newInstance(buf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getPackage(String p) {
        Class c = null;
        try {
            c = Class.forName(p + ".ObjectFactory");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (c != null) {
            return p;
        }
        int pos = p.lastIndexOf(".");
        if (pos == -1) {
            return null;
        }
        return getPackage(p.substring(0, pos));
    }

}
