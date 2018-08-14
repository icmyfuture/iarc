package cn.icmyfuture.iarc.data.solr.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

@SolrDocument(solrCoreName = "user")
@Getter
@Setter
public class SolrUser implements Serializable {
    @Id
    private String id;
    private String name;
}
