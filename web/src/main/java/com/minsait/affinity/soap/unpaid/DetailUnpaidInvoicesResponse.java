//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.03.17 a las 11:26:04 AM CET 
//


package com.minsait.affinity.soap.unpaid;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Detail_UnpaidInvoices_Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Detail_UnpaidInvoices_Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Status" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Items" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="SalesOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ChannelDist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="Customer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="InvoiceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="InvoiceDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="Debit_Credit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="CompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DocumentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ItemText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="HeaderText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Detail_UnpaidInvoices_Response", propOrder = {
    "status",
    "items"
})
public class DetailUnpaidInvoicesResponse {

    @XmlElement(name = "Status")
    protected DetailUnpaidInvoicesResponse.Status status;
    @XmlElement(name = "Items")
    protected List<DetailUnpaidInvoicesResponse.Items> items;

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link DetailUnpaidInvoicesResponse.Status }
     *     
     */
    public DetailUnpaidInvoicesResponse.Status getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailUnpaidInvoicesResponse.Status }
     *     
     */
    public void setStatus(DetailUnpaidInvoicesResponse.Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetailUnpaidInvoicesResponse.Items }
     * 
     * 
     */
    public List<DetailUnpaidInvoicesResponse.Items> getItems() {
        if (items == null) {
            items = new ArrayList<DetailUnpaidInvoicesResponse.Items>();
        }
        return this.items;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="SalesOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ChannelDist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="Customer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="InvoiceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="InvoiceDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="Debit_Credit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="CompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DocumentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ItemText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="HeaderText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "salesOrg",
        "channelDist",
        "customer",
        "invoiceNumber",
        "invoiceDate",
        "debitCredit",
        "amount",
        "currency",
        "companyCode",
        "documentType",
        "dueDate",
        "itemText",
        "headerText"
    })
    public static class Items {

        @XmlElement(name = "SalesOrg")
        protected String salesOrg;
        @XmlElement(name = "ChannelDist")
        protected String channelDist;
        @XmlElement(name = "Customer")
        protected String customer;
        @XmlElement(name = "InvoiceNumber")
        protected String invoiceNumber;
        @XmlElement(name = "InvoiceDate")
        protected String invoiceDate;
        @XmlElement(name = "Debit_Credit")
        protected String debitCredit;
        @XmlElement(name = "Amount")
        protected String amount;
        @XmlElement(name = "Currency")
        protected String currency;
        @XmlElement(name = "CompanyCode")
        protected String companyCode;
        @XmlElement(name = "DocumentType")
        protected String documentType;
        @XmlElement(name = "DueDate")
        protected String dueDate;
        @XmlElement(name = "ItemText")
        protected String itemText;
        @XmlElement(name = "HeaderText")
        protected String headerText;

        /**
         * Obtiene el valor de la propiedad salesOrg.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesOrg() {
            return salesOrg;
        }

        /**
         * Define el valor de la propiedad salesOrg.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesOrg(String value) {
            this.salesOrg = value;
        }

        /**
         * Obtiene el valor de la propiedad channelDist.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChannelDist() {
            return channelDist;
        }

        /**
         * Define el valor de la propiedad channelDist.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChannelDist(String value) {
            this.channelDist = value;
        }

        /**
         * Obtiene el valor de la propiedad customer.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomer() {
            return customer;
        }

        /**
         * Define el valor de la propiedad customer.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomer(String value) {
            this.customer = value;
        }

        /**
         * Obtiene el valor de la propiedad invoiceNumber.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        /**
         * Define el valor de la propiedad invoiceNumber.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInvoiceNumber(String value) {
            this.invoiceNumber = value;
        }

        /**
         * Obtiene el valor de la propiedad invoiceDate.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInvoiceDate() {
            return invoiceDate;
        }

        /**
         * Define el valor de la propiedad invoiceDate.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInvoiceDate(String value) {
            this.invoiceDate = value;
        }

        /**
         * Obtiene el valor de la propiedad debitCredit.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDebitCredit() {
            return debitCredit;
        }

        /**
         * Define el valor de la propiedad debitCredit.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDebitCredit(String value) {
            this.debitCredit = value;
        }

        /**
         * Obtiene el valor de la propiedad amount.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmount() {
            return amount;
        }

        /**
         * Define el valor de la propiedad amount.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmount(String value) {
            this.amount = value;
        }

        /**
         * Obtiene el valor de la propiedad currency.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurrency() {
            return currency;
        }

        /**
         * Define el valor de la propiedad currency.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCurrency(String value) {
            this.currency = value;
        }

        /**
         * Obtiene el valor de la propiedad companyCode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCompanyCode() {
            return companyCode;
        }

        /**
         * Define el valor de la propiedad companyCode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCompanyCode(String value) {
            this.companyCode = value;
        }

        /**
         * Obtiene el valor de la propiedad documentType.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDocumentType() {
            return documentType;
        }

        /**
         * Define el valor de la propiedad documentType.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDocumentType(String value) {
            this.documentType = value;
        }

        /**
         * Obtiene el valor de la propiedad dueDate.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDueDate() {
            return dueDate;
        }

        /**
         * Define el valor de la propiedad dueDate.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDueDate(String value) {
            this.dueDate = value;
        }

        /**
         * Obtiene el valor de la propiedad itemText.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getItemText() {
            return itemText;
        }

        /**
         * Define el valor de la propiedad itemText.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setItemText(String value) {
            this.itemText = value;
        }

        /**
         * Obtiene el valor de la propiedad headerText.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHeaderText() {
            return headerText;
        }

        /**
         * Define el valor de la propiedad headerText.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHeaderText(String value) {
            this.headerText = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "statusCode",
        "errorMessage"
    })
    public static class Status {

        @XmlElement(name = "StatusCode")
        protected String statusCode;
        @XmlElement(name = "ErrorMessage")
        protected String errorMessage;

        /**
         * Obtiene el valor de la propiedad statusCode.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatusCode() {
            return statusCode;
        }

        /**
         * Define el valor de la propiedad statusCode.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatusCode(String value) {
            this.statusCode = value;
        }

        /**
         * Obtiene el valor de la propiedad errorMessage.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrorMessage() {
            return errorMessage;
        }

        /**
         * Define el valor de la propiedad errorMessage.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrorMessage(String value) {
            this.errorMessage = value;
        }

    }

}
