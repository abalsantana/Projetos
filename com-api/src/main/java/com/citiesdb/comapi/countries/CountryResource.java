package com.citiesdb.comapi.countries;

import com.citiesdb.comapi.countries.Country;
import com.citiesdb.comapi.countries.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


@RestController
@RequestMapping("/countries")
public class CountryResource {


    private final CountryRepository repository;

    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public Page<Country> countries(Pageable page) {
        return repository.findAll(page);
    }

    //Pega um país pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Country> getOne(@PathVariable Long id) {
        Optional<Country> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}



