package com.batch.practice.repository;

import com.batch.practice.domain.Content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Created by 강홍구 on 2017-01-14.
 */
@Repository
@Transactional
public interface ContentRepository extends JpaRepository<Content, Long> {

}
