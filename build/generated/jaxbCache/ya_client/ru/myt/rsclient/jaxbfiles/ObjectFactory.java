//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.17 at 10:47:52 PM MSK 
//


package ru.myt.rsclient.jaxbfiles;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.myt.rsclient.jaxbfiles package. 
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

    private final static QName _Date_QNAME = new QName("", "date");
    private final static QName _PerPage_QNAME = new QName("", "per_page");
    private final static QName _Code_QNAME = new QName("", "code");
    private final static QName _Arrival_QNAME = new QName("", "arrival");
    private final static QName _Type_QNAME = new QName("", "type");
    private final static QName _Title_QNAME = new QName("", "title");
    private final static QName _Duration_QNAME = new QName("", "duration");
    private final static QName _Uid_QNAME = new QName("", "uid");
    private final static QName _Number_QNAME = new QName("", "number");
    private final static QName _Total_QNAME = new QName("", "total");
    private final static QName _ShortTitle_QNAME = new QName("", "short_title");
    private final static QName _PopularTitle_QNAME = new QName("", "popular_title");
    private final static QName _TransportType_QNAME = new QName("", "transport_type");
    private final static QName _HasNext_QNAME = new QName("", "has_next");
    private final static QName _Page_QNAME = new QName("", "page");
    private final static QName _Stops_QNAME = new QName("", "stops");
    private final static QName _Departure_QNAME = new QName("", "departure");
    private final static QName _StationType_QNAME = new QName("", "station_type");
    private final static QName _ArrivalPlatform_QNAME = new QName("", "arrival_platform");
    private final static QName _PageCount_QNAME = new QName("", "page_count");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.myt.rsclient.jaxbfiles
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Codes }
     * 
     */
    public Codes createCodes() {
        return new Codes();
    }

    /**
     * Create an instance of {@link Iata }
     * 
     */
    public Iata createIata() {
        return new Iata();
    }

    /**
     * Create an instance of {@link Icao }
     * 
     */
    public Icao createIcao() {
        return new Icao();
    }

    /**
     * Create an instance of {@link Sirena }
     * 
     */
    public Sirena createSirena() {
        return new Sirena();
    }

    /**
     * Create an instance of {@link Pagination }
     * 
     */
    public Pagination createPagination() {
        return new Pagination();
    }

    /**
     * Create an instance of {@link Vehicle }
     * 
     */
    public Vehicle createVehicle() {
        return new Vehicle();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link To }
     * 
     */
    public To createTo() {
        return new To();
    }

    /**
     * Create an instance of {@link From }
     * 
     */
    public From createFrom() {
        return new From();
    }

    /**
     * Create an instance of {@link DeparturePlatform }
     * 
     */
    public DeparturePlatform createDeparturePlatform() {
        return new DeparturePlatform();
    }

    /**
     * Create an instance of {@link Threads }
     * 
     */
    public Threads createThreads() {
        return new Threads();
    }

    /**
     * Create an instance of {@link Thread }
     * 
     */
    public Thread createThread() {
        return new Thread();
    }

    /**
     * Create an instance of {@link Carrier }
     * 
     */
    public Carrier createCarrier() {
        return new Carrier();
    }

    /**
     * Create an instance of {@link ExpressType }
     * 
     */
    public ExpressType createExpressType() {
        return new ExpressType();
    }

    /**
     * Create an instance of {@link ArrivalTerminal }
     * 
     */
    public ArrivalTerminal createArrivalTerminal() {
        return new ArrivalTerminal();
    }

    /**
     * Create an instance of {@link DepartureTerminal }
     * 
     */
    public DepartureTerminal createDepartureTerminal() {
        return new DepartureTerminal();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "date")
    public JAXBElement<XMLGregorianCalendar> createDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Date_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "per_page")
    public JAXBElement<Byte> createPerPage(Byte value) {
        return new JAXBElement<Byte>(_PerPage_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "code")
    public JAXBElement<String> createCode(String value) {
        return new JAXBElement<String>(_Code_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arrival")
    public JAXBElement<String> createArrival(String value) {
        return new JAXBElement<String>(_Arrival_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type")
    public JAXBElement<String> createType(String value) {
        return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "title")
    public JAXBElement<String> createTitle(String value) {
        return new JAXBElement<String>(_Title_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "duration")
    public JAXBElement<BigDecimal> createDuration(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Duration_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "uid")
    public JAXBElement<String> createUid(String value) {
        return new JAXBElement<String>(_Uid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "number")
    public JAXBElement<Short> createNumber(Short value) {
        return new JAXBElement<Short>(_Number_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "total")
    public JAXBElement<Byte> createTotal(Byte value) {
        return new JAXBElement<Byte>(_Total_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "short_title")
    public JAXBElement<String> createShortTitle(String value) {
        return new JAXBElement<String>(_ShortTitle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "popular_title")
    public JAXBElement<String> createPopularTitle(String value) {
        return new JAXBElement<String>(_PopularTitle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "transport_type")
    public JAXBElement<String> createTransportType(String value) {
        return new JAXBElement<String>(_TransportType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "has_next")
    public JAXBElement<String> createHasNext(String value) {
        return new JAXBElement<String>(_HasNext_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "page")
    public JAXBElement<Byte> createPage(Byte value) {
        return new JAXBElement<Byte>(_Page_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "stops")
    public JAXBElement<String> createStops(String value) {
        return new JAXBElement<String>(_Stops_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "departure")
    public JAXBElement<String> createDeparture(String value) {
        return new JAXBElement<String>(_Departure_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "station_type")
    public JAXBElement<String> createStationType(String value) {
        return new JAXBElement<String>(_StationType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arrival_platform")
    public JAXBElement<String> createArrivalPlatform(String value) {
        return new JAXBElement<String>(_ArrivalPlatform_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "page_count")
    public JAXBElement<Byte> createPageCount(Byte value) {
        return new JAXBElement<Byte>(_PageCount_QNAME, Byte.class, null, value);
    }

}
