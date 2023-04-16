package Repository.FileRepository;

import Repository.memory.RepoInMemory;

public abstract class FileRepos<ID, T> extends RepoInMemory<ID, T> {

    protected String filename;

    public FileRepos(String filename) {
        this.filename = filename;
        readFromFile();
    }

    abstract protected void readFromFile();

    abstract protected void writeToFile();

    @Override
    public boolean add(ID id, T elem) {
        if(!super.add(id, elem))
            return false;
        writeToFile();
        return true;
    }

    @Override
    public boolean update(ID id, T elem) {
        if(!super.update(id, elem))
            return false;
        writeToFile();
        return true;
    }

    @Override
    public boolean remove(ID id) {
        if(!super.remove(id))
            return false;
        writeToFile();
        return true;
    }

}
