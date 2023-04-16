package Repository.memory;

import Repository.IRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class RepoInMemory<ID, T> implements IRepo<ID, T> {

    public Map<ID, T> repository;

    public RepoInMemory() {
        this.repository = new HashMap<>();
    }

    @Override
    public boolean add(ID id, T entity) {
        return repository.put(id, entity) == null;
    }

    @Override
    public boolean remove(ID id) {
        return repository.remove(id) != null;
    }

    @Override
    public boolean update(ID id, T entity) {
        return repository.replace(id, entity) != null;

    }

    @Override
    public T findById(ID id) {
        return repository.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Integer size() {
        return repository.size();
    }
}
