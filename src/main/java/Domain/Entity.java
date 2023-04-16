package Domain;

public interface Entity<ID>{

    public void setID(ID id);

    public ID getID();

    @Override
    public boolean equals(Object o);
}
