package com.basf.challenge.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "patents")
public class Patent {

    @Id
    private String id;
    private String year;
    private String title;
    private String abstrct;
    private List<NamedEntities> nerOutput;

}
