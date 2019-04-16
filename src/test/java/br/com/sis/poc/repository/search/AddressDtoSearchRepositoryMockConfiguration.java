package br.com.sis.poc.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of AddressDtoSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class AddressDtoSearchRepositoryMockConfiguration {

    @MockBean
    private AddressDtoSearchRepository mockAddressDtoSearchRepository;

}
