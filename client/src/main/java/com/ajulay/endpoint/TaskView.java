
package com.ajulay.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for taskView complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="taskView"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="projectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://endpoint.ajulay.com/}status" minOccurs="0"/&gt;
 *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskView", propOrder = {
        "content",
        "id",
        "priority",
        "projectId",
        "status",
        "term"
})
public class TaskView {

    protected String content;
    protected String id;
    protected int priority;
    protected String projectId;
    @XmlSchemaType(name = "string")
    protected Status status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar term;

    /**
     * Gets the value of the content property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the priority property.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the projectId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProjectId(String value) {
        this.projectId = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link Status }
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link Status }
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the term property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getTerm() {
        return term;
    }

    /**
     * Sets the value of the term property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setTerm(XMLGregorianCalendar value) {
        this.term = value;
    }

}
