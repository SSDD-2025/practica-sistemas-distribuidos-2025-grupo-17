package es.codeurjc.web.controller.rest;

import java.util.Collection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.data.domain.Page;

import es.codeurjc.web.services.*;
import es.codeurjc.web.dto.cast.CastBasicDTO;
import es.codeurjc.web.dto.cast.CastDTO;
import es.codeurjc.web.dto.cast.CreateCastDTO;

@RestController
@RequestMapping("/api/cast")
public class CastRestController {
    @Autowired
    private CastService castService;

    @GetMapping("/")
    public Collection<CastDTO> getAllCast() {
        return castService.findAll();
    }

    @GetMapping("/{id}")
    public CastDTO getCast(@PathVariable long id) {
        return castService.findById(id);
    }

    @PostMapping("/")
    public CastDTO createCast(@RequestBody CreateCastDTO cast) throws IOException, SQLException {
        return castService.save(cast);
    }

    @DeleteMapping("/{id}")
    public CastDTO deleteCast(@PathVariable long id) {
        return castService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CastDTO> replaceCast(@PathVariable long id, @RequestBody CastBasicDTO updatedCastDTO)
            throws IOException {
        if (castService.exist(id)) {
            CastDTO cast = castService.update(id, updatedCastDTO);
            return new ResponseEntity<>(cast, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getCastImage(@PathVariable long id) {
        try {
            InputStream inputStream = castService.getCastImage(id);
            byte[] imageBytes = inputStream.readAllBytes();
            ByteArrayResource postImage = new ByteArrayResource(imageBytes);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(postImage);

        } catch (SQLException | IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cargar la imagen: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Object> replaceCastImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        castService.replaceCastImage(id, imageFile.getInputStream(), imageFile.getSize());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteCastImage(@PathVariable long id) throws IOException {

        castService.deleteCastImage(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<CastDTO>> getPaginatedCast(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<CastDTO> paginatedCast = castService.findAllPaginated(pageable);
        return ResponseEntity.ok(paginatedCast);
    }
}
