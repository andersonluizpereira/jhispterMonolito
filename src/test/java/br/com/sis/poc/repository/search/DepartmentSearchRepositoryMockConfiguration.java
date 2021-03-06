package br.com.sis.poc.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of DepartmentSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class DepartmentSearchRepositoryMockConfiguration {

    @MockBean
    private DepartmentSearchRepository mockDepartmentSearchRepository;

}
