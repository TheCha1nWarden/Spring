package com.my.homework;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    @Query(value = """
            SELECT * FROM users u
            WHERE :usernameFilter IS NULL OR u.username LIKE :usernameFilter
            AND :emailFilter IS NULL OR u.email LIKE :emailFilter
            """, nativeQuery = true)
    List<User> usersByFilter(String titleFilter, String emailFilter);



}