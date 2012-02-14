import html.Attributes;
import html.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ReadDomWriteJavaGenerator {

	public static void main(String argv[]) throws IOException {

		try {

			File fXmlFile = new File("./dukecal.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("event");
			System.out.println("-----------------------");
			Tag html = new Tag("html");
			Tag body = new Tag("body");
		       
			// a simple header
			Tag head = new Tag("head");
			Tag title = new Tag("title");
			title.add("HTML generator example");
			head.add(title);
			body.add(head);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Element startTime= (Element) eElement.getElementsByTagName("start").item(0);
					Element endTime= (Element) eElement.getElementsByTagName("end").item(0);
					String output;
					
					System.out.println("Summary : "
							+ getTagValue("summary", eElement));
					output="Summary : "+ getTagValue("summary", eElement);
					System.out.println("StartTime : "
							+ getTagValue("longdate", startTime)+"  "+getTagValue("time", startTime));
					output+="  StartTime : "+ getTagValue("longdate", startTime)+"  "+getTagValue("time", startTime);
					System.out.println("EndTime : "
							+ getTagValue("longdate", endTime)+"  "+getTagValue("time", endTime));
					output+="  EndTime : "
							+ getTagValue("longdate", endTime)+"  "+getTagValue("time", endTime);
					
					Tag a =new Tag("a");
					Attribute v=new Attribute("href","http://www.facebook.com");
					Attributes attr =new Attributes(v);
					a.setAttributes(attr);
					Tag p=new Tag("p");
					a.add(output);
					body.add(a);
					body.add(p);
				/*	System.out.println("Salary : "
							+ getTagValue("salary", eElement));*/

				}
			}
			html.add(body);
			FileWriter fw = new FileWriter("./test.html");
		       BufferedWriter bw = new BufferedWriter(fw);    
		       bw.write(html.toString());
		       bw.flush();
		       bw.close();
		       fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		////////////////////
		/*Div div = new Div();
		div.setId("mydiv").setCSSClass("myclass");

		A link = new A();
		link.setHref("http://www.xiaonei.com").setTarget("_blank");
		                
		div.appendChild( link );
		                
		Img image = new Img( "some alt", "git.png" );
		image.setCSSClass( "frame" ).setId( "myimageid" );
		link.appendChild( image );
		                
	System.out.println(div.write());
		/////////////////////////////////////////////////*/

	
		
		
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}