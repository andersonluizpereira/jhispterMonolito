package br.com.sis.poc.web.rest;

import br.com.sis.poc.ErpApp;

import br.com.sis.poc.domain.AddressDto;
import br.com.sis.poc.repository.AddressDtoRepository;
import br.com.sis.poc.repository.search.AddressDtoSearchRepository;
import br.com.sis.poc.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static br.com.sis.poc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AddressDtoResource REST controller.
 *
 * @see AddressDtoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErpApp.class)
public class AddressDtoResourceIntTest {

    private static final String DEFAULT_ADDRESS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRYFAKE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRYFAKE = "BBBBBBBBBB";

    private static final String DEFAULT_GEO_COORDINATE = "AAAAAAAAAA";
    private static final String UPDATED_GEO_COORDINATE = "BBBBBBBBBB";

    private static final String DEFAULT_NEIGHBORHOOD = "AAAAAAAAAA";
    private static final String UPDATED_NEIGHBORHOOD = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_ENTERPRISEID = "AAAAAAAAAA";
    private static final String UPDATED_ENTERPRISEID = "BBBBBBBBBB";

    @Autowired
    private AddressDtoRepository addressDtoRepository;

    /**
     * This repository is mocked in the br.com.sis.poc.repository.search test package.
     *
     * @see br.com.sis.poc.repository.search.AddressDtoSearchRepositoryMockConfiguration
     */
    @Autowired
    private AddressDtoSearchRepository mockAddressDtoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAddressDtoMockMvc;

    private AddressDto addressDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AddressDtoResource addressDtoResource = new AddressDtoResource(addressDtoRepository, mockAddressDtoSearchRepository);
        this.restAddressDtoMockMvc = MockMvcBuilders.standaloneSetup(addressDtoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressDto createEntity(EntityManager em) {
        AddressDto addressDto = new AddressDto()
            .addressName(DEFAULT_ADDRESS_NAME)
            .addressType(DEFAULT_ADDRESS_TYPE)
            .city(DEFAULT_CITY)
            .complement(DEFAULT_COMPLEMENT)
            .country(DEFAULT_COUNTRY)
            .countryfake(DEFAULT_COUNTRYFAKE)
            .geoCoordinate(DEFAULT_GEO_COORDINATE)
            .neighborhood(DEFAULT_NEIGHBORHOOD)
            .number(DEFAULT_NUMBER)
            .postalCode(DEFAULT_POSTAL_CODE)
            .receiverName(DEFAULT_RECEIVER_NAME)
            .reference(DEFAULT_REFERENCE)
            .state(DEFAULT_STATE)
            .street(DEFAULT_STREET)
            .enterpriseid(DEFAULT_ENTERPRISEID);
        return addressDto;
    }

    @Before
    public void initTest() {
        addressDto = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddressDto() throws Exception {
        int databaseSizeBeforeCreate = addressDtoRepository.findAll().size();

        // Create the AddressDto
        restAddressDtoMockMvc.perform(post("/api/address-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressDto)))
            .andExpect(status().isCreated());

        // Validate the AddressDto in the database
        List<AddressDto> addressDtoList = addressDtoRepository.findAll();
        assertThat(addressDtoList).hasSize(databaseSizeBeforeCreate + 1);
        AddressDto testAddressDto = addressDtoList.get(addressDtoList.size() - 1);
        assertThat(testAddressDto.getAddressName()).isEqualTo(DEFAULT_ADDRESS_NAME);
        assertThat(testAddressDto.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testAddressDto.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testAddressDto.getComplement()).isEqualTo(DEFAULT_COMPLEMENT);
        assertThat(testAddressDto.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testAddressDto.getCountryfake()).isEqualTo(DEFAULT_COUNTRYFAKE);
        assertThat(testAddressDto.getGeoCoordinate()).isEqualTo(DEFAULT_GEO_COORDINATE);
        assertThat(testAddressDto.getNeighborhood()).isEqualTo(DEFAULT_NEIGHBORHOOD);
        assertThat(testAddressDto.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testAddressDto.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testAddressDto.getReceiverName()).isEqualTo(DEFAULT_RECEIVER_NAME);
        assertThat(testAddressDto.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testAddressDto.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testAddressDto.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testAddressDto.getEnterpriseid()).isEqualTo(DEFAULT_ENTERPRISEID);

        // Validate the AddressDto in Elasticsearch
        verify(mockAddressDtoSearchRepository, times(1)).save(testAddressDto);
    }

    @Test
    @Transactional
    public void createAddressDtoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressDtoRepository.findAll().size();

        // Create the AddressDto with an existing ID
        addressDto.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressDtoMockMvc.perform(post("/api/address-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressDto)))
            .andExpect(status().isBadRequest());

        // Validate the AddressDto in the database
        List<AddressDto> addressDtoList = addressDtoRepository.findAll();
        assertThat(addressDtoList).hasSize(databaseSizeBeforeCreate);

        // Validate the AddressDto in Elasticsearch
        verify(mockAddressDtoSearchRepository, times(0)).save(addressDto);
    }

    @Test
    @Transactional
    public void getAllAddressDtos() throws Exception {
        // Initialize the database
        addressDtoRepository.saveAndFlush(addressDto);

        // Get all the addressDtoList
        restAddressDtoMockMvc.perform(get("/api/address-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressDto.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressName").value(hasItem(DEFAULT_ADDRESS_NAME.toString())))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].complement").value(hasItem(DEFAULT_COMPLEMENT.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].countryfake").value(hasItem(DEFAULT_COUNTRYFAKE.toString())))
            .andExpect(jsonPath("$.[*].geoCoordinate").value(hasItem(DEFAULT_GEO_COORDINATE.toString())))
            .andExpect(jsonPath("$.[*].neighborhood").value(hasItem(DEFAULT_NEIGHBORHOOD.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].receiverName").value(hasItem(DEFAULT_RECEIVER_NAME.toString())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
            .andExpect(jsonPath("$.[*].enterpriseid").value(hasItem(DEFAULT_ENTERPRISEID.toString())));
    }
    
    @Test
    @Transactional
    public void getAddressDto() throws Exception {
        // Initialize the database
        addressDtoRepository.saveAndFlush(addressDto);

        // Get the addressDto
        restAddressDtoMockMvc.perform(get("/api/address-dtos/{id}", addressDto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(addressDto.getId().intValue()))
            .andExpect(jsonPath("$.addressName").value(DEFAULT_ADDRESS_NAME.toString()))
            .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.complement").value(DEFAULT_COMPLEMENT.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.countryfake").value(DEFAULT_COUNTRYFAKE.toString()))
            .andExpect(jsonPath("$.geoCoordinate").value(DEFAULT_GEO_COORDINATE.toString()))
            .andExpect(jsonPath("$.neighborhood").value(DEFAULT_NEIGHBORHOOD.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER.toString()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.receiverName").value(DEFAULT_RECEIVER_NAME.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.enterpriseid").value(DEFAULT_ENTERPRISEID.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAddressDto() throws Exception {
        // Get the addressDto
        restAddressDtoMockMvc.perform(get("/api/address-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddressDto() throws Exception {
        // Initialize the database
        addressDtoRepository.saveAndFlush(addressDto);

        int databaseSizeBeforeUpdate = addressDtoRepository.findAll().size();

        // Update the addressDto
        AddressDto updatedAddressDto = addressDtoRepository.findById(addressDto.getId()).get();
        // Disconnect from session so that the updates on updatedAddressDto are not directly saved in db
        em.detach(updatedAddressDto);
        updatedAddressDto
            .addressName(UPDATED_ADDRESS_NAME)
            .addressType(UPDATED_ADDRESS_TYPE)
            .city(UPDATED_CITY)
            .complement(UPDATED_COMPLEMENT)
            .country(UPDATED_COUNTRY)
            .countryfake(UPDATED_COUNTRYFAKE)
            .geoCoordinate(UPDATED_GEO_COORDINATE)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .number(UPDATED_NUMBER)
            .postalCode(UPDATED_POSTAL_CODE)
            .receiverName(UPDATED_RECEIVER_NAME)
            .reference(UPDATED_REFERENCE)
            .state(UPDATED_STATE)
            .street(UPDATED_STREET)
            .enterpriseid(UPDATED_ENTERPRISEID);

        restAddressDtoMockMvc.perform(put("/api/address-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAddressDto)))
            .andExpect(status().isOk());

        // Validate the AddressDto in the database
        List<AddressDto> addressDtoList = addressDtoRepository.findAll();
        assertThat(addressDtoList).hasSize(databaseSizeBeforeUpdate);
        AddressDto testAddressDto = addressDtoList.get(addressDtoList.size() - 1);
        assertThat(testAddressDto.getAddressName()).isEqualTo(UPDATED_ADDRESS_NAME);
        assertThat(testAddressDto.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testAddressDto.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testAddressDto.getComplement()).isEqualTo(UPDATED_COMPLEMENT);
        assertThat(testAddressDto.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testAddressDto.getCountryfake()).isEqualTo(UPDATED_COUNTRYFAKE);
        assertThat(testAddressDto.getGeoCoordinate()).isEqualTo(UPDATED_GEO_COORDINATE);
        assertThat(testAddressDto.getNeighborhood()).isEqualTo(UPDATED_NEIGHBORHOOD);
        assertThat(testAddressDto.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testAddressDto.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testAddressDto.getReceiverName()).isEqualTo(UPDATED_RECEIVER_NAME);
        assertThat(testAddressDto.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testAddressDto.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testAddressDto.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testAddressDto.getEnterpriseid()).isEqualTo(UPDATED_ENTERPRISEID);

        // Validate the AddressDto in Elasticsearch
        verify(mockAddressDtoSearchRepository, times(1)).save(testAddressDto);
    }

    @Test
    @Transactional
    public void updateNonExistingAddressDto() throws Exception {
        int databaseSizeBeforeUpdate = addressDtoRepository.findAll().size();

        // Create the AddressDto

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressDtoMockMvc.perform(put("/api/address-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addressDto)))
            .andExpect(status().isBadRequest());

        // Validate the AddressDto in the database
        List<AddressDto> addressDtoList = addressDtoRepository.findAll();
        assertThat(addressDtoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AddressDto in Elasticsearch
        verify(mockAddressDtoSearchRepository, times(0)).save(addressDto);
    }

    @Test
    @Transactional
    public void deleteAddressDto() throws Exception {
        // Initialize the database
        addressDtoRepository.saveAndFlush(addressDto);

        int databaseSizeBeforeDelete = addressDtoRepository.findAll().size();

        // Delete the addressDto
        restAddressDtoMockMvc.perform(delete("/api/address-dtos/{id}", addressDto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AddressDto> addressDtoList = addressDtoRepository.findAll();
        assertThat(addressDtoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AddressDto in Elasticsearch
        verify(mockAddressDtoSearchRepository, times(1)).deleteById(addressDto.getId());
    }

    @Test
    @Transactional
    public void searchAddressDto() throws Exception {
        // Initialize the database
        addressDtoRepository.saveAndFlush(addressDto);
        when(mockAddressDtoSearchRepository.search(queryStringQuery("id:" + addressDto.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(addressDto), PageRequest.of(0, 1), 1));
        // Search the addressDto
        restAddressDtoMockMvc.perform(get("/api/_search/address-dtos?query=id:" + addressDto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressDto.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressName").value(hasItem(DEFAULT_ADDRESS_NAME)))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].complement").value(hasItem(DEFAULT_COMPLEMENT)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].countryfake").value(hasItem(DEFAULT_COUNTRYFAKE)))
            .andExpect(jsonPath("$.[*].geoCoordinate").value(hasItem(DEFAULT_GEO_COORDINATE)))
            .andExpect(jsonPath("$.[*].neighborhood").value(hasItem(DEFAULT_NEIGHBORHOOD)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].receiverName").value(hasItem(DEFAULT_RECEIVER_NAME)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].enterpriseid").value(hasItem(DEFAULT_ENTERPRISEID)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressDto.class);
        AddressDto addressDto1 = new AddressDto();
        addressDto1.setId(1L);
        AddressDto addressDto2 = new AddressDto();
        addressDto2.setId(addressDto1.getId());
        assertThat(addressDto1).isEqualTo(addressDto2);
        addressDto2.setId(2L);
        assertThat(addressDto1).isNotEqualTo(addressDto2);
        addressDto1.setId(null);
        assertThat(addressDto1).isNotEqualTo(addressDto2);
    }
}
