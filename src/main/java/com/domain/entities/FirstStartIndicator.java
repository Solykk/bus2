package com.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by burbulet on 4/29/17.
 */
@Entity
@Getter
@NoArgsConstructor
public class FirstStartIndicator {

    @Id
    private Integer id;

    private Boolean started;

    public FirstStartIndicator(Integer id, Boolean started) {
        this.id = id;
        this.started = started;
    }
}
