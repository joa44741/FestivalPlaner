/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.joa44741.swprojektjohn.facade.entitymapper;

import de.oth.joa44741.swprojektjohn.core.dto.BaseDto;
import de.oth.joa44741.swprojektjohn.entity.AbstractLongEntity;

public interface EntityToDtoMapper<T extends BaseDto, E extends AbstractLongEntity> {

    T mapToDto(E entity);

    E mapToEntity(T dto);
}
