package Repository.memory;

import Domain.Entity;

public class EntityRepository<T extends Entity> extends RepoInMemory<String, T> {
}
