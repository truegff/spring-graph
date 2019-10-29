package codes.monkey.springgraph;

import java.util.Collection;
import org.springframework.beans.factory.config.BeanDefinition;

public class Node {
    private int id;
    private int ctxPosition;
    private String name;
    private String clazz;
    private String ctxName;
    private Collection<Node> dependsOn;
    private Collection<Node> dependantNodes;
    private BeanDefinition beanDefinition;

    public Node(String name) {
        this.name = name;
    }

    Node(int id, String name, String beanClassName, BeanDefinition beanDefinition) {
        this.id = id;
        this.name = name;
        this.clazz = beanClassName;
        this.beanDefinition = beanDefinition;
    }
    
    protected String sanitize(String s) {
        return s.replaceAll(".*\\.", "").replaceAll("[^\\w]+", "_");
    }
    
    public String getSimpleName() {
        return String.format("ctx%d_$s", ctxPosition, sanitize(name));        
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCtxPosition() {
        return ctxPosition;
    }

    public void setCtxPosition(int ctxPosition) {
        this.ctxPosition = ctxPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCtxName() {
        return ctxName;
    }

    public void setCtxName(String ctxName) {
        this.ctxName = ctxName;
    }

    public Collection<Node> getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Collection<Node> dependsOn) {
        this.dependsOn = dependsOn;
    }

    public Collection<Node> getDependantNodes() {
        return dependantNodes;
    }

    public void setDependantNodes(Collection<Node> dependantNodes) {
        this.dependantNodes = dependantNodes;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public void setBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }
    
    
}
