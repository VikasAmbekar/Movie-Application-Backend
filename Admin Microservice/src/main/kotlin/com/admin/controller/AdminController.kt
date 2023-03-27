package com.admin.controller

import com.admin.entity.Admin
import com.admin.repository.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/admin")
class AdminController(
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

}