package com.example.hbs.repository;

import com.example.hbs.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query(value = "select h from Hotel h where h.user.Id = :Id")
    Page<Hotel> findByUserId(@Param(value = "Id") Long userId, Pageable page);
}
