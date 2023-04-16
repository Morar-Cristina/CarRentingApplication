package Repository;

import java.util.List;

public interface IRepo<ID, T> {

    boolean add(ID id, T entity);

    boolean remove(ID id);

    boolean update(ID id, T entity);

    T findById(ID id);

    List<T> getAll();

    Integer size();
}
