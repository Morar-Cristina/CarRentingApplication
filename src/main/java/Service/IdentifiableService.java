package Service;

import Domain.Entity;
import Exception.MainException;
import Repository.IRepo;

import java.util.ArrayList;
import java.util.Map;


public abstract class IdentifiableService<T extends Entity<String>> {

    private final IRepo<String, T> repo;

    public IdentifiableService(IRepo<String, T> repo) {
        this.repo = repo;
    }

    public void add(Map.Entry<String, T>... mapEntries) throws MainException {
        for (Map.Entry<String, T> map : mapEntries)
            if (!repo.add(map.getKey(), map.getValue())) throw new MainException("Item already in the list!");
    }


    public void add(T... entities) throws MainException {
        for (T e : entities)
            if (!repo.add(e.getID(), e)) throw new MainException("Item already in the list!");
    }


    public void remove(Map.Entry<String, T>... mapEntries) throws MainException {
        for (Map.Entry<String, T> map : mapEntries)
            if (!repo.remove(map.getKey())) throw new MainException("Item already in the list!");
    }


    public boolean remove(T... entities) throws MainException {
        for (T e : entities)
            if (!repo.remove(e.getID())) throw new MainException("Item not found in the list!");
        return false;
    }

    public void update(Map.Entry<String, T> mapEntry) throws MainException {
        if (!repo.update(mapEntry.getKey(), mapEntry.getValue()))
            throw new MainException("Item not found in the list!");
    }

    public void update(T entity) throws MainException {
        if (!repo.update(entity.getID(), entity)) throw new MainException("Item not found in the list!");
    }

    public T get(String id) throws MainException {
        T e = repo.findById(id);
        if (e == null) throw new MainException("Item not found in the list!");
        return e;
    }

    public ArrayList<T> get() {
        return (ArrayList<T>) repo.getAll();
    }

    public boolean contains(String id) {
        return repo.findById(id) != null;
    }

    public Integer size() {
        return repo.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T e : repo.getAll())
            s.append(e.toString()).append("\n");
        return s.toString();
    }
}
