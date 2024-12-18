package br.com.nexus.goat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nexus.goat.entities.Feature;
import br.com.nexus.goat.services.FeatureService;

@RestController
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @GetMapping("/findByProductId/{idProduct}")
    public ResponseEntity<Feature> findByProductId(@PathVariable Long idProduct) {
        Feature feature = featureService.findByProductId(idProduct);
        return ResponseEntity.ok().body(feature);
    }
}
