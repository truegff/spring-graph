package codes.monkey.springgraph;

import java.util.Collection;
import java.util.stream.Collectors;

public class VisFormat implements Format<VisFormat.VisFormatStruct>{

    @Override
    public VisFormatStruct apply(Collection<Node> targets) {
        VisFormatStruct result = new VisFormatStruct();
        result.nodes = targets.stream()
                .map(target -> {
                    return new VisFormatStructNode(
                            target.getId(),
                            target.getSimpleName()
                    );
                })
                .collect(Collectors.toList());
        result.edges = targets.stream()
                .flatMap(target -> {
                    return target.getDependsOn()
                            .stream()
                            .map(depend -> {
                                return new VisFormatStructEdge(
                                        target.getId(),
                                        depend.getId(),
                                        "to");
                            });
                })
                .collect(Collectors.toList());
        return result;
    }

    static class VisFormatStruct {
        private Collection<VisFormatStructNode> nodes;
        private Collection<VisFormatStructEdge> edges;

        public Collection<VisFormatStructNode> getNodes() {
            return nodes;
        }

        public void setNodes(Collection<VisFormatStructNode> nodes) {
            this.nodes = nodes;
        }

        public Collection<VisFormatStructEdge> getEdges() {
            return edges;
        }

        public void setEdges(Collection<VisFormatStructEdge> edges) {
            this.edges = edges;
        }
        
        
    }
    
    static class VisFormatStructNode {
        private int id;
        private String label;

        public VisFormatStructNode(int id, String label) {
            this.id = id;
            this.label = label;
        }
        
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
    
    static class VisFormatStructEdge {
        private int from;
        private int to;
        private String arrow;

        public VisFormatStructEdge(int from, int to, String arrow) {
            this.from = from;
            this.to = to;
            this.arrow = arrow;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public String getArrow() {
            return arrow;
        }

        public void setArrow(String arrow) {
            this.arrow = arrow;
        }
        
        
    }
}
