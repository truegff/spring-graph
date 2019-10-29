package codes.monkey.springgraph;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GraphBuilder implements ApplicationContextAware {

    private final LinkedList<ApplicationContext> applicationContexts = new LinkedList<>();
    private Object registry;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContexts.clear();
        applicationContexts.add(context);
        while (context.getParent() != null) {
            context = context.getParent();
            applicationContexts.addFirst(context);
        }

        buildGraph();
    }

    public <T> T format(Format<T> format, List<Predicate<Node>> filters) {
        Collection<Node> targets = null;
        return format.apply(targets);
    }

    private void buildGraph() {

        AtomicInteger id = new AtomicInteger();
        
        Map<String, Node> nodeRegistry = new HashMap<>();
        BiFunction<ApplicationContext, String, Node> register = (context, name) -> {
            ConfigurableListableBeanFactory listableBeanFactory = (ConfigurableListableBeanFactory) context.getAutowireCapableBeanFactory();
            if (!nodeRegistry.containsKey(name)) {
                Node node = null;
                if (!listableBeanFactory.containsBeanDefinition(name)) {
                    node = new Node(name);
                } else {
                    BeanDefinition beanDefinition = listableBeanFactory.getBeanDefinition(name);
                    node = new Node(id.getAndIncrement(), name, beanDefinition.getBeanClassName(), beanDefinition);
                }
                nodeRegistry.put(name, node);
            }
            return nodeRegistry.get(name);
        };

//        {
//            Node node;
//            int ctxIndex;
//            ApplicationContext context;
//            ConfigurableListableBeanFactory listableBeanFactory = (ConfigurableListableBeanFactory) context.getAutowireCapableBeanFactory();
//
//            node.setCtxName(context.getDisplayName());
//            node.setCtxPosition(ctxIndex);
//            
//        }
    }

}
