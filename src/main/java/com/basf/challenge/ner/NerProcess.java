package com.basf.challenge.ner;

import com.basf.challenge.entities.NamedEntities;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NerProcess {

    private StanfordCoreNLP pipeline;

    public NerProcess() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        pipeline = new StanfordCoreNLP(props);
    }

    public List<NamedEntities> getNamedEntities(String text) {
        List<NamedEntities> listEntities = new ArrayList<>();
        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);
        doc.entityMentions().stream().forEach(coreEntityMention -> {
            NamedEntities namedEntity = new NamedEntities();
            namedEntity.setText(coreEntityMention.text());
            namedEntity.setEntityType(coreEntityMention.entityType());
            listEntities.add(namedEntity);
        });
        return listEntities;
    }

}
