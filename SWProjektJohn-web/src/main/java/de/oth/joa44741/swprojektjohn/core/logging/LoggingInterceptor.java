package de.oth.joa44741.swprojektjohn.core.logging;

import java.io.Serializable;
import java.util.Arrays;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.jboss.logging.Logger;

/**
 *
 * @author Andreas John
 */
@Interceptor
@DoLogging
public class LoggingInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private transient Logger logger;

    @AroundInvoke
    public Object logMethodCalls(InvocationContext context) {
        final Long startTime = System.currentTimeMillis();
        final Object[] parameters = context.getParameters();
        final String methodName = context.getMethod().getName();
        final String className = context.getMethod().getDeclaringClass().getSimpleName();
        Object result = null;
        try {
            logger.info(className + "." + methodName + " called with parameters: " + Arrays.toString(parameters));
            result = context.proceed();
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            final Long milliseconds = System.currentTimeMillis() - startTime;

            logger.info(className + "." + methodName + " returned: " + result + " in " + milliseconds + " ms.");
        }
    }
}
