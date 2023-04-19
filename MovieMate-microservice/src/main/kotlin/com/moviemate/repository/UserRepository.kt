package com.moviemate.repository

import com.moviemate.entity.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ReactiveCrudRepository<User, Long> {


//    @Query("updateOne({_id: 7900001883}, {  \$set: {name: 'Vikas Ambekar'}})")
//    fun updateUserNameById(): Mono<User>

//    @Query("{'_id': ?0}")
//    fun updateUserNameById(id: Long?, name: String?): Mono<Void?>?
}

