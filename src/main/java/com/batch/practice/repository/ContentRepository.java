package com.batch.practice.repository;

import com.batch.practice.domain.Content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Created by 강홍구 on 2017-01-14.
 */
@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}
