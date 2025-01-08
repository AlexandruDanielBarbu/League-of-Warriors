package Interfaces;

import Common.Entity;

public interface Visitor <T extends Entity> {
    void visit(T entity);
}

