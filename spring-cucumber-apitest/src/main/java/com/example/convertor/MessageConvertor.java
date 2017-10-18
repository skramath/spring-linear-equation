package com.example.convertor;

import com.example.response.CoeffReponse;
/*import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
*/

public class MessageConvertor {
	
	public static void write(CoeffReponse response) {

        
      /*  XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("product", Product.class);
        System.out.println(xstream.toXML(product));	*/	
		
	}
	static void read(){
		/*String json = "{\"product\":{\"name\":\"Banana\",\"id\":123"
			    + ",\"price\":23.0}}";
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("product", Product.class);
		Product product = (Product)xstream.fromXML(json);
		System.out.println(product.getName());*/
	}
	
	
}
