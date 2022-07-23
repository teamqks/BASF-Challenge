package com.basf.challenge.entities.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "questel-patent-document")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatentInput {

    @XmlElement(name = "bibliographic-data")
    private DateAndTitleInput dateAndTitle;
    @XmlElement(name = "abstract")
    private AbstractInput abstrct;

}
