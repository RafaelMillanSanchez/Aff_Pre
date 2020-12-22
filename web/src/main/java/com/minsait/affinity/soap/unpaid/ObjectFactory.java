//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.03.17 a las 11:26:04 AM CET 
//


package com.minsait.affinity.soap.unpaid;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.minsait.affinity.soap.unpaid package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DetailUnpaidInvoicesRequest_QNAME = new QName("http://affinity-petcare.es/sales_force/sd/unpaid_invoices", "Detail_UnpaidInvoices_Request");
    private final static QName _DetailUnpaidInvoicesResponse_QNAME = new QName("http://affinity-petcare.es/sales_force/sd/unpaid_invoices", "Detail_UnpaidInvoices_Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.minsait.affinity.soap.unpaid
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DetailUnpaidInvoicesResponse }
     * 
     */
    public DetailUnpaidInvoicesResponse createDetailUnpaidInvoicesResponse() {
        return new DetailUnpaidInvoicesResponse();
    }

    /**
     * Create an instance of {@link DetailUnpaidInvoicesRequest }
     * 
     */
    public DetailUnpaidInvoicesRequest createDetailUnpaidInvoicesRequest() {
        return new DetailUnpaidInvoicesRequest();
    }

    /**
     * Create an instance of {@link DetailUnpaidInvoicesResponse.Status }
     * 
     */
    public DetailUnpaidInvoicesResponse.Status createDetailUnpaidInvoicesResponseStatus() {
        return new DetailUnpaidInvoicesResponse.Status();
    }

    /**
     * Create an instance of {@link DetailUnpaidInvoicesResponse.Items }
     * 
     */
    public DetailUnpaidInvoicesResponse.Items createDetailUnpaidInvoicesResponseItems() {
        return new DetailUnpaidInvoicesResponse.Items();
    }

    /**
     * Create an instance of {@link DetailUnpaidInvoicesRequest.Items }
     * 
     */
    public DetailUnpaidInvoicesRequest.Items createDetailUnpaidInvoicesRequestItems() {
        return new DetailUnpaidInvoicesRequest.Items();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailUnpaidInvoicesRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DetailUnpaidInvoicesRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://affinity-petcare.es/sales_force/sd/unpaid_invoices", name = "Detail_UnpaidInvoices_Request")
    public JAXBElement<DetailUnpaidInvoicesRequest> createDetailUnpaidInvoicesRequest(DetailUnpaidInvoicesRequest value) {
        return new JAXBElement<DetailUnpaidInvoicesRequest>(_DetailUnpaidInvoicesRequest_QNAME, DetailUnpaidInvoicesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailUnpaidInvoicesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DetailUnpaidInvoicesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://affinity-petcare.es/sales_force/sd/unpaid_invoices", name = "Detail_UnpaidInvoices_Response")
    public JAXBElement<DetailUnpaidInvoicesResponse> createDetailUnpaidInvoicesResponse(DetailUnpaidInvoicesResponse value) {
        return new JAXBElement<DetailUnpaidInvoicesResponse>(_DetailUnpaidInvoicesResponse_QNAME, DetailUnpaidInvoicesResponse.class, null, value);
    }

}
