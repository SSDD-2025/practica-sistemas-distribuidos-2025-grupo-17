package es.codeurjc.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.codeurjc.web.services.*;
import es.codeurjc.web.entities.*;

@Controller
public class CastController {

    private static final String CAST_IMAGES_FOLDER = "cast_images";

    @Autowired
	private CastService castService;

    @Autowired
	private ImageService imageService;

    @Autowired
	private MoviesService moviesService;

    @GetMapping("/cast/{id}")
	public String showCast(Model model, @PathVariable long id) {

		Cast cast = castService.findById(id);

		model.addAttribute("cast", cast);

		return "cast_template";
	}

    @GetMapping("/cast/{id}/image")	
	public ResponseEntity<Object> downloadCastImage(@PathVariable int id) throws MalformedURLException {

		return imageService.createResponseFromImage(CAST_IMAGES_FOLDER, id);		
	}

    @GetMapping("/cast/new")
	public String newCastForm(Model model) {
		model.addAttribute("movies",moviesService.findAll());
		return "new_cast_template";
	}
	
	@PostMapping("/cast/new")
	public String newCast(Model model, @RequestParam List<Long> castMovies,@RequestParam String castName, @RequestParam String castBiography,@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date castBirthDate, @RequestParam String castWorkfield, @RequestParam String castOriginCountry, MultipartFile castImage) throws IOException {

		List<Movie> moviesList=new ArrayList<Movie>();
		for (int i=0;i<castMovies.size();i++){
			moviesList.add(moviesService.findById(castMovies.get(i)));
		}
        Cast cast=new Cast(castName,castBiography,castBirthDate,castWorkfield,castOriginCountry,moviesList);
		castService.save(cast);
		
		imageService.saveImage(CAST_IMAGES_FOLDER, cast.getId(), castImage);

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}

    @PostMapping("/cast/{id}/delete")
	public String deleteCast(Model model, @PathVariable long id) throws IOException {

		castService.deleteById(id);

		imageService.deleteImage(CAST_IMAGES_FOLDER, id);

		model.addAttribute("movies", moviesService.findAll());
		model.addAttribute("cast",castService.findAll());

		return "home_template";
	}

	@GetMapping("/cast/{id}/modify")
	public String modifyCastForm(Model model,@PathVariable long id) {
		Cast cast=castService.findById(id);
		model.addAttribute("cast",cast);
		return "modify_cast_template";
	}
	
	@PostMapping("/cast/{id}/modify")
	public String modifyCast(Model model, @PathVariable long id,@RequestParam String castName, @RequestParam String castBiography,@RequestParam Date castBirthDate, @RequestParam String castWorkfield, @RequestParam String originCountry, MultipartFile image) throws IOException {

        Cast cast=castService.findById(id);
		cast.setBiography(castBiography);
		cast.setBirthDate(castBirthDate);
		cast.setName(castName);
		cast.setOriginCountry(originCountry);
		cast.setWorkField(castWorkfield);
		
		imageService.deleteImage(CAST_IMAGES_FOLDER, cast.getId());
		imageService.saveImage(CAST_IMAGES_FOLDER, cast.getId(), image);

		model.addAttribute("cast",cast);

		return "cast_template";
	}
}