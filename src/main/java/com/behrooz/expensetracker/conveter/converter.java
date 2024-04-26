package com.behrooz.expensetracker.conveter;

public abstract class converter<E,D> {

   public abstract D toDto(E entity);
   public abstract E toEntity(D dto);
}
