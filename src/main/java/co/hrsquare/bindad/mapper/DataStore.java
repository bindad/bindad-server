package co.hrsquare.bindad.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Slf4j
public class DataStore implements ApplicationContextAware {
    private ApplicationContext context;

    public <M, T> void save(Class<M> mapperClass, T t) {
        try {
            Method insertMethod = Arrays.stream(mapperClass.getDeclaredMethods())
                    .filter(m -> m.getName().equals("insert"))
                    .findFirst().orElseThrow(RuntimeException::new);

            Method findIdMethod = Arrays.stream(mapperClass.getDeclaredMethods())
                    .filter(m -> m.getName().equals("findId"))
                    .findFirst().orElseThrow(RuntimeException::new);

            Method setIdMethod = Arrays.stream(t.getClass().getDeclaredMethods())
                    .filter(m -> m.getName().equals("setId"))
                    .findFirst().orElseThrow(RuntimeException::new);

            //insert
            M mapperBean = context.getBean(mapperClass);
            insertMethod.invoke(mapperBean, t);

            //get id
            Long id = (Long) findIdMethod.invoke(mapperBean, t);

            //set id
            setIdMethod.invoke(t, id);
        } catch (Exception e) {
            log.error("Error saving {}", t, e);
            throw new RuntimeException(e);
        }
    }

    public <M> void hardDeleteBy(Class<M> mapperClass, String deleteByMethodName, Long id) {
        try {
            Method deleteByMethod = Arrays.stream(mapperClass.getDeclaredMethods())
                    .filter(m -> m.getName().equals(deleteByMethodName))
                    .findFirst().orElseThrow(RuntimeException::new);

            M mapperBean = context.getBean(mapperClass);
            deleteByMethod.invoke(mapperBean, id);
        } catch (Exception e) {
            log.error("Error on hard delete: {}", mapperClass, e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
