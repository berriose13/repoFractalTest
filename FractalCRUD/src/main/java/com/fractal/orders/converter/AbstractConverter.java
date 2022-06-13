package com.fractal.orders.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {
  
  public abstract E toEntity(D dto);
  
  public abstract D toDTO(E entity);
  
  public List<E> toEntityList(List<D> dtos){

     if(dtos == null) {
       return null; 
     }
     return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList()); 
  }
  
  public List<D> toDtoList(List<E> entitys){
       if(entitys == null) {
       return null; 
     }
    return entitys.stream() .map(entity -> toDTO(entity)).collect(Collectors.toList()); 
  }
  
  /*public List<E> fromDto(List<D> dtos){ 
     if(dtos == null) {
       return null; 
     }
     return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList()); 
   } 
    
  public List<D> fromEntity(List<E> entities){ 
     if(entities == null) {
       return null; 
     }
    return entities.stream() .map(entity -> toDTO(entity)).collect(Collectors.toList()); 
   }*/
}
