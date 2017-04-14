package proxy;

import library.models.Book;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by admin on 11.04.2017.
 */
public class ProxyDataManager implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()) {
            case ("readExternal"):
                break;
        }
        Book book = new Book("Mick Jagger", "Rock-n-roll", 2017, "23423");
        return book;
    }
}
