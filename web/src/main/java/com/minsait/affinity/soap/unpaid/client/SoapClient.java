package com.minsait.affinity.soap.unpaid.client;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.minsait.affinity.soap.unpaid.DetailUnpaidInvoicesRequest;
import com.minsait.affinity.soap.unpaid.DetailUnpaidInvoicesResponse;
import com.minsait.affinity.soap.unpaid.ObjectFactory;

@Service
public class SoapClient extends WebServiceGatewaySupport {
	
	@Autowired
	private WebServiceTemplate template;
	
	
	public DetailUnpaidInvoicesResponse getUnpaidCustomers(DetailUnpaidInvoicesRequest request) {
		//Marshaller marshaller = context.createMarshaller();
        //marshaller.marshal(authentication, soapHeader.getResult());

		//template = new WebServiceTemplate(marshaller);
		ObjectFactory of = new ObjectFactory();
		JAXBElement<DetailUnpaidInvoicesRequest> reqjaxb = of.createDetailUnpaidInvoicesRequest(request);
		@SuppressWarnings("unchecked")
		JAXBElement<DetailUnpaidInvoicesResponse> response = (JAXBElement<DetailUnpaidInvoicesResponse>) template.marshalSendAndReceive("http://sapintrq.affinity-petcare.com:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=Sys_AFFINITY_SALES_FORCE&receiverParty=&receiverService=&interface=os_Unpaid_Invoices&interfaceNamespace=http%3A%2F%2Faffinity-petcare.es%2Fsales_force%2Fsd%2Funpaid_invoices", 
				reqjaxb);
		return response.getValue();
	}
	
}
