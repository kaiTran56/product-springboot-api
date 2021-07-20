package com.tranquyet.convert;

public interface BasedConvert<T, H> {
	
	T toDTO(H h);
	
	H toEntity(T t);
	
	H toEntity(T t, H h);

}
