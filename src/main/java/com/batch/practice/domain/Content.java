package com.batch.practice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by 강홍구 on 2016-12-11.
 */
@Entity
public class Content {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_content_member"), name = "member_fk_id")
    private Member member;

    @Lob
    @Column(nullable = false)
    @JsonProperty
    private String content;

    @Column(nullable = false)
    @JsonProperty
    private DateTime createdAt;

    public Boolean isAfterCreatedDate (DateTime date) {
        return this.createdAt.isAfter(date);
    }


    public void modifyContent (String newContent) {
        this.content = newContent;
    }
}
