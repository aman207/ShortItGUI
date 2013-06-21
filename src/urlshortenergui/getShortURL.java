package urlshortenergui;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 *
 * @author aman207
 */
public class getShortURL {
    
    public static String getURL(String URLget)
    {
       Pattern p = Pattern.compile("(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))");
		Matcher m = p.matcher(URLget);
		StringBuffer sb = new StringBuffer();  
		while (m.find())  
		 {  
		    URLget=m.group(1);
			
		String URL="http://begr.im/yourls-api.php?signature=26dc86d493&action=shorturl&url="+ URLget;
		if (URLget.startsWith("http://")||URLget.startsWith("www."))
		{
			try {				
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	    		Document doc = docBuilder.parse(new InputSource(new URL(URL).openStream()));
	    		
	            NodeList nodeList = doc.getElementsByTagName("shorturl");
			    
	            for (int temp = 0; temp < nodeList.getLength(); temp++) {

	                Node nNode = nodeList.item(temp);
	                Element eElement = (Element) nNode;
	                if(eElement.getAttribute("shorturl") != null)
	                {
	                	URLget=eElement.getTextContent();

	                }
	                else
	                {

	                }

	            }

		}
		
	       catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error occured");
		}  catch (SAXException e) {
			System.err.println("You either entered in an invalid URL, or our URL shortener services are down. Please try again.");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		}
		else
		{
			
		}
		m.appendReplacement(sb, "");
		sb.append(URLget);
			
		 }
		m.appendTail(sb);
		return (sb.toString()); 
    }
}
