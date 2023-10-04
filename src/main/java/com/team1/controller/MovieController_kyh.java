package com.team1.controller;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.team1.dto.MovieVo;
import com.team1.exception.CategoryException;
import com.team1.service.MovieService;

@Controller
//@RequestMapping("/movie")
public class MovieController_kyh {

    @Autowired
    private MovieService movieService;  

    @GetMapping()
    public String requestMovieList(Model model) { 
        List<MovieVo> list = movieService.getAllMovieList();
        model.addAttribute("movieList", list);  
        return "movies"; 
    } 

    @GetMapping("/all")  
    public ModelAndView requestAllMovies() {
        ModelAndView modelAndView = new ModelAndView();  
        List<MovieVo> list = movieService.getAllMovieList();
        modelAndView.addObject("movieList", list);  
        modelAndView.setViewName("movies");  
        return modelAndView; 
    }
    
    @GetMapping("/{category}") 
    public String requestMoviesByCategory(@PathVariable("category") String movieCategory, Model model) {  
        List<MovieVo> moviesByCategory =movieService.getMovieListByCategory(movieCategory);  
        
        if (moviesByCategory == null || moviesByCategory.isEmpty()) {
            throw new CategoryException();
        }
        
        model.addAttribute("movieList", moviesByCategory);  
        return "movies";   
    }
    
    @GetMapping("/filter/{movieFilter}")
    public String requestMoviesByFilter(
    @MatrixVariable(pathVar="movieFilter") Map<String,List<String>> movieFilter, 
    Model model) {
        List<MovieVo> moviesByFilter = movieService.getMovieListByFilter(movieFilter);
        model.addAttribute("movieList", moviesByFilter);
        return "movies";
    }
    
    @GetMapping("/movie")
    public String requestMovieById(@RequestParam("id") String movieId, Model model) {  
    	List<MovieVo> movieById = movieService.getMovieById(movieId);
        model.addAttribute("movie", movieById );
        return "movie";
    }

    @GetMapping("/add")  
    public String requestAddMovieForm(@ModelAttribute("NewMovie") MovieVo movieVo) {  
        return "addMovie";
    }  

    @ModelAttribute  
    public void addAttributes(Model model) { 
        model.addAttribute("addTitle", "신규 영화 등록");  
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	
    	//binder.setValidator(unitsInStockValidator);  // 생성한 unitsInStockValidator 설정
//    	 binder.setValidator(movieValidator);  
        binder.setAllowedFields("movieId","name","unitPrice","author", "description", 
        "publisher","category","unitsInStock","totalPages", "releaseDate", "condition", "movieImage"); 
    }
    
    
    @GetMapping("/update")  
    public String getUpdateMovieForm(@ModelAttribute("updateMovie") MovieVo movieVo, @RequestParam("id") String movieId, Model model) {
    	List<MovieVo> movieById = movieService.getMovieById(movieId);
        model.addAttribute("movie", movieById);
        return "updateForm";
    }  
 
    
    @RequestMapping(value = "/delete") 
    public String getDeleteMovieForm(Model model, @RequestParam("id") String movieId) {
    	movieService.setDeleteMovie(movieId);
        return "redirect:/movies";
    }
    
}