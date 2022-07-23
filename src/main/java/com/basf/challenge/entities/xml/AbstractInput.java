package com.basf.challenge.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "abstract")
@XmlAccessorType(XmlAccessType.FIELD)
public class AbstractInput {

    @XmlElement(name = "p")
    private String abstrct;

}
