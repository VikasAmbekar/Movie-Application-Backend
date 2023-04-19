package com.admin.controller

import com.admin.entity.Admin
import com.admin.entity.Movie
import com.admin.entity.Theater
import com.admin.repository.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/admin")
class AdminController(
    val restTemplate: RestTemplate,

    @Autowired
    val adminRepository: AdminRepository
) {

    @GetMapping("/")
    fun getAllAdmins():Flux<Admin> {
        return adminRepository.findAll()
    }

    @GetMapping("/{adminMobileNo}")
    fun getAdminByMobile(@PathVariable adminMobileNo:Long):Mono<Admin>{
        return adminRepository.findById(adminMobileNo)
    }

    @PostMapping("/")
    fun addAdmin(@RequestBody admin: Admin):Mono<Admin>{
        return adminRepository.save(admin)
    }

    @PutMapping("/{adminMobileNo}")
    fun updateAdmin(@PathVariable adminMobileNo: Long,@RequestBody admin: Admin):Mono<Admin>{
        return adminRepository.save(admin)
    }

    @DeleteMapping("/{adminMobileNo}")
    fun deleteAdmin(@PathVariable adminMobileNo: Long):Mono<Void>{
        return adminRepository.deleteById(adminMobileNo)
    }

    @DeleteMapping("/all")
    fun deleteAdminAll():Mono<Void>{
        return adminRepository.deleteAll()
    }

    // ---------------------------------- MOVIE FROM ADMIN OPERATIONS ------------------------

    val url = "http://localhost:8700/movie/"
//    val url = "http://movie-application/movie/"

    @GetMapping("/movie")
    fun getAllMovieFromAdmin(): Array<Movie>? {
    return restTemplate.getForObject(url,Array<Movie>::class.java)
    }

    @PostMapping("/add-movie")
    fun addMovieFromAdmin(@RequestBody movie: Movie): ResponseEntity<Movie> {
        return restTemplate.postForEntity(url,movie,Movie::class.java)
    }

    @PutMapping("/update-movie/{id}")
    fun updateMovieFromAdmin(@PathVariable id : String, @RequestBody movie: Movie): ResponseEntity<Movie> {
        val url1 = "http://localhost:8700/movie/$id"
        restTemplate.put(url1,movie)
        val resp = restTemplate.getForEntity(url1,Movie::class.java)
        return resp
    }

    @DeleteMapping("/delete-movie/{id}")
    fun deleteMovieFromAdmin(@PathVariable id: String):String {
        val url1 = "http://localhost:8700/movie/$id"
        restTemplate.delete(url1)
        return "Movie with ID : $id deleted."
    }

    // -------------------------------------------- || ---------------------------------


    // -------------------------------------------- THEATER OPERATIONS FROM ADMIN --------------------------------

    val urlt = "http://localhost:8700/theater/"

    @GetMapping("/theater/")
    fun getAllTheaterFromAdmin():Array<Theater>?{
        return restTemplate.getForObject(urlt,Array<Theater>::class.java)
    }

    @PostMapping("/add-theater")
    fun addTheaterFromAdmin(@RequestBody theater: Theater):ResponseEntity<Theater>{
        return restTemplate.postForEntity(urlt,theater,Theater::class.java)
    }

    @PutMapping("/update-theater/{id}")
    fun putTheaterFromAdmin(@PathVariable id: String,@RequestBody theater: Theater):ResponseEntity<Theater>{
        val urlt1 = "http://localhost:8700/theater/$id"
        restTemplate.put(urlt1,theater)
        val resp = restTemplate.getForEntity(urlt1,Theater::class.java)
        return resp
    }

    @DeleteMapping("/delete-theater/{id}")
    fun deleteTheaterFromAdmin(@PathVariable id: String):String{
        val urlt1 = "http://localhost:8700/theater/$id"
        restTemplate.delete(urlt1)
        return "Theater with ID : $id deleted."
    }


}