package com.behrooz.expensetracker.conveter;

public abstract class converter<E,D> {

   protected abstract D toDto(E entity);
   protected abstract E toEntity(D dto);
}
