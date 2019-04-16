package br.com.sis.poc.repository;

import br.com.sis.poc.domain.AddressDto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AddressDto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressDtoRepository extends JpaRepository<AddressDto, Long> {

}
