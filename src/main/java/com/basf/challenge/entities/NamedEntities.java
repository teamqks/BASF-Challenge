package com.basf.challenge.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NamedEntities {

    private String text;
    private String entityType;

}
