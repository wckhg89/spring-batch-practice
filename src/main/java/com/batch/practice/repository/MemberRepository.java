package com.batch.practice.repository;

import com.batch.practice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kanghonggu on 2017. 3. 21..
 */
@Repository
@Transactional
public interface MemberRepository extends JpaRepository <Member, Long>{

    List<Member> findAll();

}
