package pl.fis.daos;

import java.util.List;

public interface ObjectDAO<T>
{
	void addObject(T object);
	List<T> getObjects();
	T getObject(long id);
}
