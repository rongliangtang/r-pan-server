package com.tangrl.pan.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解析SpEl表达式解析器工具类
 * Spring Expression Language (SpEL) 是一种功能强大的表达式语言，用于在 Spring 应用程序中查询和操作对象图。
 * SpEL 允许开发者在 XML、注解和 Java 代码中使用类似于 EL (Expression Language) 的语法来动态访问和操作对象。
 */
public class SpElUtil {

    private static final RPanExpressionEvaluator expressionEvaluator = new RPanExpressionEvaluator();

    /**
     * 解析SpEl表达式
     *
     * @param spElExpression
     * @param returnType
     * @param className
     * @param methodName
     * @param classType
     * @param method
     * @param args
     * @param parameterTypes
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T getCustomerValue(String spElExpression,
                                         Class<T> returnType,
                                         String className,
                                         String methodName,
                                         Class classType,
                                         Method method,
                                         Object[] args,
                                         Class[] parameterTypes,
                                         Object target) {
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(className, methodName, classType, method, args, parameterTypes, target);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, classType);
        return expressionEvaluator.getValueWithCustomerType(spElExpression, methodKey, evaluationContext, returnType);
    }

    /**
     * 解析SpEll表达式
     *
     * @param spElExpression
     * @param className
     * @param methodName
     * @param classType
     * @param method
     * @param args
     * @param parameterTypes
     * @param target
     * @return
     */
    public static Object getValue(String spElExpression,
                                  String className,
                                  String methodName,
                                  Class classType,
                                  Method method,
                                  Object[] args,
                                  Class[] parameterTypes,
                                  Object target) {
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(className, methodName, classType, method, args, parameterTypes, target);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, classType);
        return expressionEvaluator.getValue(spElExpression, methodKey, evaluationContext);
    }

    /**
     * 解析SpEl表达式
     *
     * @param spElExpression
     * @param className
     * @param methodName
     * @param classType
     * @param method
     * @param args
     * @param parameterTypes
     * @param target
     * @return
     */
    public static String getStringValue(String spElExpression,
                                        String className,
                                        String methodName,
                                        Class classType,
                                        Method method,
                                        Object[] args,
                                        Class[] parameterTypes,
                                        Object target) {
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(className, methodName, classType, method, args, parameterTypes, target);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, classType);
        return expressionEvaluator.getValueWithStringType(spElExpression, methodKey, evaluationContext);
    }

    /**
     * 表达式根对象
     * 内部静态类，用于创建 SpEL 表达式的根对象。它包含了方法执行的相关信息。
     * 该对象主要支持以下表达式格式：
     * #root.className
     * #root.methodName
     * #root.classType
     * #root.method
     * #root.args
     * #root.parameterTypes
     * #root.target
     */
    @Data
    @AllArgsConstructor
    private static class RPanExpressionRootObject {

        /**
         * 切点方法所属类名称
         */
        private String className;

        /**
         * 切点方法名称
         */
        private String methodName;

        /**
         * 类的Class
         */
        private Class classType;

        /**
         * 代理方法实体
         */
        private Method method;

        /**
         * 切点方法传参
         */
        private Object[] args;

        /**
         * 切点方法传参类型
         */
        private Class[] parameterTypes;

        /**
         * 代理对象实体
         */
        private Object target;

    }

    /**
     * 表达式执行器对象
     * 内部静态类，继承了 CachedExpressionEvaluator，用于缓存和解析 SpEL 表达式。
     */
    @Data
    private static class RPanExpressionEvaluator extends CachedExpressionEvaluator {
        private final ParameterNameDiscoverer paramNameDiscoverer = new DefaultParameterNameDiscoverer();
        private final Map<ExpressionKey, Expression> conditionCache = new ConcurrentHashMap<>(256);
        private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(256);

        /**
         * 创建表达式执行器上下文对象
         *
         * @param className
         * @param methodName
         * @param classType
         * @param method
         * @param args
         * @param parameterTypes
         * @param target
         * @return
         */
        private EvaluationContext createEvaluationContext(String className,
                                                          String methodName,
                                                          Class classType,
                                                          Method method,
                                                          Object[] args,
                                                          Class[] parameterTypes,
                                                          Object target) {
            Method targetMethod = getTargetMethod(classType, method);
            RPanExpressionRootObject root = new RPanExpressionRootObject(className, methodName, classType, method, args, parameterTypes, target);
            return new MethodBasedEvaluationContext(root, targetMethod, args, this.paramNameDiscoverer);
        }

        /**
         * 表达式解析，解析结果自动转化成指定类型
         *
         * @param conditionExpression
         * @param elementKey
         * @param evalContext
         * @param clazz
         * @return
         */
        public <T> T getValueWithCustomerType(String conditionExpression, AnnotatedElementKey elementKey, EvaluationContext evalContext, Class<T> clazz) {
            return getExpression(this.conditionCache, elementKey, conditionExpression).getValue(evalContext, clazz);
        }

        /**
         * 表达式解析，解析结果不自动转化
         *
         * @param conditionExpression
         * @param elementKey
         * @param evalContext
         * @return
         */
        public Object getValue(String conditionExpression, AnnotatedElementKey elementKey, EvaluationContext evalContext) {
            Expression expression = getExpression(this.conditionCache, elementKey, conditionExpression);
            return expression.getValue(evalContext);
        }

        /**
         * 表达式解析，解析结果自动转化成String
         *
         * @param conditionExpression
         * @param elementKey
         * @param evalContext
         * @return
         */
        public String getValueWithStringType(String conditionExpression, AnnotatedElementKey elementKey, EvaluationContext evalContext) {
            Object value = getValue(conditionExpression, elementKey, evalContext);
            if (Objects.nonNull(value)) {
                return value.toString();
            }
            return StringUtils.EMPTY;
        }

        /**
         * 获取缓存的目标方法
         *
         * @param targetClass
         * @param method
         * @return
         */
        private Method getTargetMethod(Class<?> targetClass, Method method) {
            AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
            Method targetMethod = this.targetMethodCache.get(methodKey);
            if (targetMethod == null) {
                targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
                if (targetMethod == null) {
                    targetMethod = method;
                }
                this.targetMethodCache.put(methodKey, targetMethod);
            }
            return targetMethod;
        }
    }

}
