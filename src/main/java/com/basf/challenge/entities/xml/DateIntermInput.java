package com.basf.challenge.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "dates-of-public-availability")
@XmlAccessorType(XmlAccessType.FIELD)
public class DateIntermInput {

    @XmlElement(name = "publication-of-grant-date")
    private DateLeafInput date;

}
