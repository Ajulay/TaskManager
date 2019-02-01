
package com.ajulay.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for saveTask complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="saveTask"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://endpoint.ajulay.com/}session" minOccurs="0"/&gt;
 *         &lt;element name="arg1" type="{http://endpoint.ajulay.com/}taskView" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveTask", propOrder = {
        "arg0",
        "arg1"
})
public class SaveTask {

    protected Session arg0;
    protected TaskView arg1;

    /**
     * Gets the value of the arg0 property.
     *
     * @return possible object is
     * {@link Session }
     */
    public Session getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     *
     * @param value allowed object is
     *              {@link Session }
     */
    public void setArg0(Session value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     *
     * @return possible object is
     * {@link TaskView }
     */
    public TaskView getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     *
     * @param value allowed object is
     *              {@link TaskView }
     */
    public void setArg1(TaskView value) {
        this.arg1 = value;
    }

}
