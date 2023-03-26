package com.jwtproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jwtproject.model.Design;
import com.jwtproject.repository.DesignRepository;
import com.jwtproject.service.MetaService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class DesignController {
	
	
  @Autowired MetaService metaService;
  
  @GetMapping("/Design-dashboard")
  public String dashboard(Model model) {

      var files = metaService.listDesign();
      model.addAttribute("files", files);
      return "design";
  }

  @Autowired
  DesignRepository designRepository;

  @GetMapping("/designs")
  public ResponseEntity<List<Design>> getAllDesigns(@RequestParam(required = false) String title) {
    try {
      List<Design> designs = new ArrayList<Design>();

      if (title == null)
        designRepository.findAll().forEach(designs::add);
      else
        designRepository.findByTitleContaining(title).forEach(designs::add);

      if (designs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(designs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/design/{id}")
  public ResponseEntity<Design> getTutorialById(@PathVariable("id") String id) {
    Optional<Design> designData = designRepository.findById(id);

    if (designData.isPresent()) {
      return new ResponseEntity<>(designData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/design/{title}")
  public ResponseEntity<Design> getTutorialByTitle(@PathVariable("title") String title) {
    Optional<Design> designData = designRepository.findByTitle(title);

    if (designData.isPresent()) {
      return new ResponseEntity<>(designData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @GetMapping("/design/{description}")
  public ResponseEntity<Design> getTutorialByDescription(@PathVariable("description") String description) {
    Optional<Design> designData = designRepository.findDesignByDescription(description);

    if (designData.isPresent()) {
      return new ResponseEntity<>(designData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  
  @PostMapping("/designs")
  public ResponseEntity<Design> createTutorial(@RequestBody Design design) {
    try {
      Design _design = designRepository.save(design);
      return new ResponseEntity<>(_design, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/designs/{id}")
  public ResponseEntity<Design> updateDesign(@PathVariable("id") String id, @RequestBody Design design) {
    Optional<Design> designData = designRepository.findById(id);

    if (designData.isPresent()) {
      Design _design = designData.get();
      _design.setTitle(design.getTitle());
      _design.setDescription(design.getDescription());
      _design.setCreator(design.getCreator());
      _design.setPrice(design.getPrice());
      _design.setCreation_date(design.getCreation_date());
      _design.setLast_update_date(design.getLast_update_date());
      _design.setTags(design.getTags());
      return new ResponseEntity<>(designRepository.save(_design), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/designs/{id}")
  public ResponseEntity<HttpStatus> deleteDesign(@PathVariable("id") String id) {
    try {
      designRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/designs")
  public ResponseEntity<HttpStatus> deleteAllDesigns() {
    try {
      designRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/upload-design")
  public String upload(@RequestParam("file") MultipartFile file) throws IOException {
      try {
    	  metaService.uploadDesign(file);
          return new String("Uploaded to S3 successfully!!!");
        } catch (Exception e) {
          return new String("INTERNAL_SERVER_ERROR");
        }
  }
}
