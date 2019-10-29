package codes.monkey.springgraph;

import java.util.Collection;

interface Format<T> {
    T apply(Collection<Node> targets);
}
