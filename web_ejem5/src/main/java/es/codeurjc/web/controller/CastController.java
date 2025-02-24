package es.codeurjc.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

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

	@GetMapping("/castList")
	public String showCastList(Model model){
		model.addAttribute("cast", castService.findAll());
		return "castList_template";
	}

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
	public String newCast(Model model, @RequestParam(value = "castMovies", required = false) List<Long> castMovies,@RequestParam String castName, @RequestParam String castBiography,@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date castBirthDate, @RequestParam String castOriginCountry, MultipartFile castImage) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	String castBirthDateCorrect = sdf.format(castBirthDate);
		Cast cast;
		if (castMovies!=null){
			List<Movie> moviesList=new ArrayList<Movie>();
			for (int i=0;i<castMovies.size();i++){
				moviesList.add(moviesService.findById(castMovies.get(i)));
			}
			cast=new Cast(castName,castBiography,castBirthDateCorrect,castOriginCountry,moviesList);
		} else{
			cast=new Cast(castName,castBiography,castBirthDateCorrect,castOriginCountry,null);
		}
		castService.save(cast);
		
		imageService.saveImage(CAST_IMAGES_FOLDER, cast.getId(), castImage);
		return "cast_created_template";
	}

    @PostMapping("/cast/{id}/delete")
	public String deleteCast(Model model, @PathVariable long id) throws IOException {

		castService.deleteById(id);

		imageService.deleteImage(CAST_IMAGES_FOLDER, id);

		return "cast_deleted_template";
	}

	@GetMapping("/cast/{id}/modify")
	public String modifyCastForm(Model model,@PathVariable long id) {
		Cast cast=castService.findById(id);
		model.addAttribute("cast",cast);
		model.addAttribute("allMovies", moviesService.findAll());
		return "modify_cast_template";
	}
	
	@PostMapping("/cast/{id}/modify")
	public String modifyCast(Model model,@RequestParam(value = "castMovies", required = false) List<Long> castMovies,@PathVariable long id,@RequestParam String castName, @RequestParam String castBiography,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date castBirthDate, @RequestParam String castOriginCountry, MultipartFile castImage) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	String castBirthDateCorrect = sdf.format(castBirthDate);
		Cast cast=castService.findById(id);
		if (castMovies!=null){
			List<Movie> moviesList=new ArrayList<Movie>();
			for (int i=0;i<castMovies.size();i++){
				moviesList.add(moviesService.findById(castMovies.get(i)));
			}
			cast.setMovies(moviesList);
		} else{
			cast.setMovies(null);
		}
		cast.setBiography(castBiography);
		cast.setBirthDate(castBirthDateCorrect);
		cast.setName(castName);
		cast.setOriginCountry(castOriginCountry);
		
		imageService.deleteImage(CAST_IMAGES_FOLDER, cast.getId());
		imageService.saveImage(CAST_IMAGES_FOLDER, cast.getId(), castImage);

		model.addAttribute("cast",cast);

		return "cast_modified_template";
	}
}