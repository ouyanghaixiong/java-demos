package com.ouyanghaixiong.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;

/**
 * @author ouyanghaixiong@forchange.tech
 * @date 2020/8/9 - 9:34 下午
 * @desc 反射工具
 */
public class ReflectComponent {

    /**
     * 反编译类
     *
     * @param classFullName 全类名
     * @return 类定义
     */
    public String decompile(String classFullName) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(classFullName);
        // 接受结果
        StringBuilder s = new StringBuilder();
        // class
        Annotation[] annotations = aClass.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            s.append(String.format("@%s\n", annotation.annotationType().getSimpleName()));
        }
        String classModifiers = Modifier.toString(aClass.getModifiers());
        String className = aClass.getSimpleName();
        String superClassTypeName = aClass.getGenericSuperclass().getTypeName();
        s.append(String.format("%s class %s extends %s ", classModifiers, className, superClassTypeName));
        Type[] interfaces = aClass.getGenericInterfaces();
        if (interfaces.length != 0) {
            s.append("implements ");
        }
        for (Type interfaceType : interfaces) {
            String interfaceTypeName = interfaceType.getTypeName();
            s.append(String.format("%s,", interfaceTypeName));
        }
        if (interfaces.length != 0) {
            s.deleteCharAt(s.length() - 1);
        }
        s.append("{\n");
        // field
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldModifiers = Modifier.toString(field.getModifiers());
            String genericType = field.getGenericType().getTypeName();
            String fieldName = field.getName();
            s.append(String.format("\t%s %s %s;\n", fieldModifiers, genericType, fieldName));
        }
        // method
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] methodAnnotations = method.getDeclaredAnnotations();
            for (Annotation annotation : methodAnnotations) {
                String annotationName = annotation.annotationType().getSimpleName();
                s.append(String.format("@%s\n", annotationName));
            }
            String modifiers = Modifier.toString(method.getModifiers());
            String returnType = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            s.append(String.format("\t%s %s %s(", modifiers, returnType, methodName));
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                s.append(String.format("%s arg%d,", parameterTypes[i].getName(), i + 1));
            }
            if (parameterTypes.length != 0) {
                s.deleteCharAt(s.length() - 1);
            }
            s.append(") {}\n");
        }
        s.append("}");
        return s.toString();
    }

    /**
     * 调用类的空参构造方法实例化对象
     *
     * @param classFullName 全类名
     * @param <T>           反射得到的类型
     * @return 实例对象, 所有属性都是默认值
     */
    public <T> T newInstance(String classFullName, Class<T> clazz) throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        Class<T> aClass = (Class<T>) Class.forName(classFullName);
        return aClass.newInstance();
    }

    /**
     * 调用对象的特定方法
     *
     * @return 方法的返回值
     */
    public <T> Object invokeMethod(Object obj, Class<T> clazz, String methodName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取类型
        Class<T> aClass = (Class<T>) obj.getClass();
        // 获取args的真实类型
        ArrayList<Class<?>> argTypes = new ArrayList<>();
        for (Object arg : args) {
            // 这里获取的是arg的真实类型, 而不是Object
            Class<?> argType = arg.getClass();
            argTypes.add(argType);
        }
        // 获取特定方法
        Method method = aClass.getDeclaredMethod(methodName, argTypes.toArray(new Class<?>[]{}));
        // 调用方法
        return method.invoke(obj, args);
    }
}
