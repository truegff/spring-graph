package codes.monkey.springgraph;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DotFormat implements Format<String> {

    @Override
    public String apply(Collection<Node> targets) {
        List<String> items = targets.stream().map(node -> {
            List<String> dependentNodeNames = node.getDependsOn().stream().map(dep -> dep.getSimpleName()).collect(Collectors.toList());
            String dependsOnString = String.join(" ", dependentNodeNames);
            return String.format("%s -> {%s}", node.getSimpleName(), dependsOnString);
        }).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {").append("\n");
        sb.append("rankdir=LR;");
        items.forEach(item -> {
            sb.append(item).append("\n");
        });
        sb.append("}");
        return sb.toString();
    }

}
