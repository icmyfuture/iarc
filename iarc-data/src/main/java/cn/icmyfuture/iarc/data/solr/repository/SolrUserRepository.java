package cn.icmyfuture.iarc.data.solr.repository;

import cn.icmyfuture.iarc.data.solr.document.SolrUser;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrUserRepository extends SolrCrudRepository<SolrUser, String> {
    List<SolrUser> findByNameContaining(String name);
}
