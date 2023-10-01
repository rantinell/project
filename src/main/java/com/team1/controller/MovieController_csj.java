package com.team1.controller;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.team1.dto.MovieDTO;
import com.team1.exception.CategoryException;
import com.team1.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController_csj {

    @Autowired
    private MovieService movieService;  

    @GetMapping
    public String requestMovieList(Model model) { 
        List<MovieDTO> list = movieService.getAllMovieList();
        model.addAttribute("movieList", list);  
        return "movies"; 
    } 
    
 /*   @GetMapping("/all")
    public String requestAllmovies(Model model) {  
        List<movie> list = movieService.getAllmovieList(); 
        model.addAttribute("movieList", list); 
        return "movies";
    } 
   */ 
    @GetMapping("/all")  
    public ModelAndView requestAllMovies() {
        ModelAndView modelAndView = new ModelAndView();  
        List<MovieDTO> list = movieService.getAllMovieList();
        modelAndView.addObject("movieList", list);  
        modelAndView.setViewName("movies");  
        return modelAndView; 
    }
    
    @GetMapping("/{category}") 
    public String requestMoviesByCategory(@PathVariable("category") String movieCategory, Model model) {  
        List<MovieDTO> moviesByCategory =movieService.getMovieListByCategory(movieCategory);  
        
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
        List<MovieDTO> moviesByFilter = movieService.getMovieListByFilter(movieFilter);
        model.addAttribute("movieList", moviesByFilter);
        return "movies";
    }
    
    @GetMapping("/movie")
    public String requestMovieById(@RequestParam("id") String movieId, Model model) {  
    	List<MovieDTO> movieById = movieService.getMovieById(movieId);
        model.addAttribute("movie", movieById );
        return "movie";
    }
    
    /*@GetMapping("/add")  
    public String requestAddMovieForm(movie movie) {  
        return "addmovie";  
    }  
    */
    
    @GetMapping("/add")  
    public String requestAddMovieForm(@ModelAttribute("NewMovie") MovieDTO movieDTO) {  
        return "addMovie";
    }  

//   내가 맡은 파트가 아닌 거 같아서 보류	 
//    @PostMapping("/add") 
//    public String submitAddNewMovie(@Valid @ModelAttribute("NewMovie")  MovieDTO movieDTO, BindingResult result) {
//    	 
//    	if(result.hasErrors()) { 
//             return "addMovie";
//         } 
//    
//    	 
//    	MultipartFile movieImage = movieDTO.getPoster();  
//
//        String saveName = movieImage.getOriginalFilename();  
//        File saveFile = new File("C:\\upload", saveName); 
//        
//        if (movieImage != null && !movieImage.isEmpty()) {
//            try {
//            	movieImage.transferTo(saveFile);  
//            	movieDTO.setFileName(saveName);
//            } catch (Exception e) {
//                throw new RuntimeException("영화 이미지 업로드가 실패하였습니다", e);
//            }
//        }
//    	
//        movieService.setNewMovie(movieDTO); 
//        return "redirect:/movies"; 
//    } 
//    
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
    
//    영화 로드 같음  
//    @ExceptionHandler(value={MovieIdException.class}) 
//    public ModelAndView handleError(HttpServletRequest req, MovieIdException exception) {
//         ModelAndView mav = new ModelAndView();  
//         mav.addObject("invalidMovieId", exception.getMovieId());  
//         mav.addObject("exception", exception);  
//         mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());  
//         mav.setViewName("errorMovie");  
//         return mav;  
//    }
    
    @GetMapping("/update")  
    public String getUpdateMovieForm(@ModelAttribute("updateMovie") MovieDTO movieDTO, @RequestParam("id") String movieId, Model model) {
    	List<MovieDTO> movieById = movieService.getMovieById(movieId);
        model.addAttribute("movie", movieById);
        return "updateForm";
    }  

//    @PostMapping("/update") 
//    public String submitUpdateMovieForm(@ModelAttribute("updateMovie") MovieDTO movieDTO) {
//        MultipartFile movieImage = movieDTO.getPoster();
//        String rootDirectory = "c:/upload/";
//        if (movieImage!=null && !movieImage.isEmpty()) {
//            try {
//                String fname = movieImage.getOriginalFilename(); 
//                movieImage.transferTo(new File("c:/upload/" + fname));
//                movieDTO.setFileName(fname);
//            } catch (Exception e) {
//                throw new RuntimeException("Movie Image saving failed", e);
//            }
//        }
//        movieService.setUpdateMovie(movieDTO);
//        return "redirect:/movies";
//    }  
    
    @RequestMapping(value = "/delete") 
    public String getDeleteMovieForm(Model model, @RequestParam("id") String movieId) {
    	movieService.setDeleteMovie(movieId);
        return "redirect:/movies";
    }
    
}