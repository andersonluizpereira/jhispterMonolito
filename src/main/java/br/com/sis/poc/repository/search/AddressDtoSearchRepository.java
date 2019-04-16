package br.com.sis.poc.repository.search;

import br.com.sis.poc.domain.AddressDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AddressDto entity.
 */
public interface AddressDtoSearchRepository extends ElasticsearchRepository<AddressDto, Long> {
}
