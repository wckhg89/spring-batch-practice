package com.batch.practice.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * Created by kanghonggu on 2017. 3. 21..
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    @JsonProperty
    private String memberId;

    @Column(nullable = false, length = 20)
    @JsonProperty
    private String password;

    @Column(nullable = false, length = 20)
    @JsonProperty
    private String name;

    @Column(nullable = true, length = 30)
    @JsonProperty
    private String email;

    @JsonProperty
    private Boolean deleted = false;
    @Column(nullable = false)


    public Long getId() {
        return id;
    }

    public Member() {

    }

    public Member(Long id, String password, String name, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void update(Member newMember) {
        this.name = StringUtils.isEmpty(newMember.name) ? this.name : newMember.name;
        this.password = StringUtils.isEmpty(newMember.password) ? this.password : newMember.password;
        this.email = StringUtils.isEmpty(newMember.email) ? this.email : newMember.email;
    }

}