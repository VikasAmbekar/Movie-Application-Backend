package com.admin.repository

import com.admin.entity.Admin
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : ReactiveCrudRepository<Admin,Long> {
}