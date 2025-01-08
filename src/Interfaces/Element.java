package Interfaces;

import Common.Entity;

public interface Element <T extends Entity> {
    void accept(Visitor<T> visitor);
}
