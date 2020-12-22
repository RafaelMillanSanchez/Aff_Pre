package com.minsait.affinity.soap.price.client;

import java.io.IOException;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapFaultDetailElement;
import org.springframework.ws.soap.client.SoapFaultClientException;

import com.minsait.affinity.soap.price.DTZSDFSALESORDERSIMULATE3;
import com.minsait.affinity.soap.price.DTZSDFSALESORDERSIMULATE3RESPONSE;
import com.minsait.affinity.soap.price.ObjectFactory;


@Service
public class SoapPriceClient extends WebServiceGatewaySupport {
	
	@Autowired
	private WebServiceTemplate template;
	
	
	public DTZSDFSALESORDERSIMULATE3RESPONSE getPrices(DTZSDFSALESORDERSIMULATE3 request) {
		//Marshaller marshaller = context.createMarshaller();
        //marshaller.marshal(authentication, soapHeader.getResult());

		//template = new WebServiceTemplate(marshaller);
		
		ObjectFactory of = new ObjectFactory();
		JAXBElement<DTZSDFSALESORDERSIMULATE3> reqjaxb = of.createMTZSDFSALESORDERSIMULATE3(request);
		@SuppressWarnings("unchecked")
		JAXBElement<DTZSDFSALESORDERSIMULATE3RESPONSE> response = (JAXBElement<DTZSDFSALESORDERSIMULATE3RESPONSE>) template.marshalSendAndReceive("http://sapintrq.affinity-petcare.com:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=Sys_AFFINITY_SALES_FORCE&receiverParty=&receiverService=&interface=os_ValoracionPedido_Detalle_2&interfaceNamespace=http%3A%2F%2Faffinity-petcare.es%2Fsales_force%2Fsd%2Fvaloracion_pedido", 
				reqjaxb);	
		return response.getValue();

	}

}
