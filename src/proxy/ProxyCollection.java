package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by admin on 11.04.2017.
 */
public class ProxyCollection implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()) {
            case("add"):
                return Boolean.TRUE;
            case("remove"):
                return Boolean.TRUE;
            case("contains"):
                Boolean[] mas = {true, false, false, true};
                Integer index = (Integer) args[0];
                if (index >= mas.length || index < 0) { return false; }
                return mas[index];
        }
        return null;
    }
}
