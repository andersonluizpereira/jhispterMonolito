package br.com.sis.poc.web.rest;
import br.com.sis.poc.domain.AddressDto;
import br.com.sis.poc.repository.AddressDtoRepository;
import br.com.sis.poc.repository.search.AddressDtoSearchRepository;
import br.com.sis.poc.web.rest.errors.BadRequestAlertException;
import br.com.sis.poc.web.rest.util.HeaderUtil;
import br.com.sis.poc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing AddressDto.
 */
@RestController
@RequestMapping("/api")
public class AddressDtoResource {

    private final Logger log = LoggerFactory.getLogger(AddressDtoResource.class);

    private static final String ENTITY_NAME = "addressDto";

    private final AddressDtoRepository addressDtoRepository;

    private final AddressDtoSearchRepository addressDtoSearchRepository;

    public AddressDtoResource(AddressDtoRepository addressDtoRepository, AddressDtoSearchRepository addressDtoSearchRepository) {
        this.addressDtoRepository = addressDtoRepository;
        this.addressDtoSearchRepository = addressDtoSearchRepository;
    }

    /**
     * POST  /address-dtos : Create a new addressDto.
     *
     * @param addressDto the addressDto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new addressDto, or with status 400 (Bad Request) if the addressDto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/address-dtos")
    public ResponseEntity<AddressDto> createAddressDto(@RequestBody AddressDto addressDto) throws URISyntaxException {
        log.debug("REST request to save AddressDto : {}", addressDto);
        if (addressDto.getId() != null) {
            throw new BadRequestAlertException("A new addressDto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressDto result = addressDtoRepository.save(addressDto);
        addressDtoSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/address-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /address-dtos : Updates an existing addressDto.
     *
     * @param addressDto the addressDto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated addressDto,
     * or with status 400 (Bad Request) if the addressDto is not valid,
     * or with status 500 (Internal Server Error) if the addressDto couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/address-dtos")
    public ResponseEntity<AddressDto> updateAddressDto(@RequestBody AddressDto addressDto) throws URISyntaxException {
        log.debug("REST request to update AddressDto : {}", addressDto);
        if (addressDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddressDto result = addressDtoRepository.save(addressDto);
        addressDtoSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, addressDto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /address-dtos : get all the addressDtos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of addressDtos in body
     */
    @GetMapping("/address-dtos")
    public ResponseEntity<List<AddressDto>> getAllAddressDtos(Pageable pageable) {
        log.debug("REST request to get a page of AddressDtos");
        Page<AddressDto> page = addressDtoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/address-dtos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /address-dtos/:id : get the "id" addressDto.
     *
     * @param id the id of the addressDto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the addressDto, or with status 404 (Not Found)
     */
    @GetMapping("/address-dtos/{id}")
    public ResponseEntity<AddressDto> getAddressDto(@PathVariable Long id) {
        log.debug("REST request to get AddressDto : {}", id);
        Optional<AddressDto> addressDto = addressDtoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(addressDto);
    }

    /**
     * DELETE  /address-dtos/:id : delete the "id" addressDto.
     *
     * @param id the id of the addressDto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/address-dtos/{id}")
    public ResponseEntity<Void> deleteAddressDto(@PathVariable Long id) {
        log.debug("REST request to delete AddressDto : {}", id);
        addressDtoRepository.deleteById(id);
        addressDtoSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/address-dtos?query=:query : search for the addressDto corresponding
     * to the query.
     *
     * @param query the query of the addressDto search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/address-dtos")
    public ResponseEntity<List<AddressDto>> searchAddressDtos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of AddressDtos for query {}", query);
        Page<AddressDto> page = addressDtoSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/address-dtos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
