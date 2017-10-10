/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.core.dto;

/**
 *
 * @author Andi
 */
public class BaseDto {

    private Long id;

    protected BaseDto(Long id) {
        this.id = id;
    }

    protected BaseDto() {

    }

    public Long getId() {
        return id;
    }

}
