package com.ouyanghaixiong.reflect;

import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author ouyanghaixiong@forchange.tech
 * @date 2020/8/9 - 10:16 下午
 * @desc
 */
public class ReflectComponentTest {

    @Test
    public void testDecompile() throws ClassNotFoundException {
        ReflectComponent reflect = new ReflectComponent();
        String baseStudent = reflect.decompile("com.ouyanghaixiong.reflect.BaseStudent");
        System.out.println(baseStudent);
        System.out.println("************");
        String complexStudent = reflect.decompile("com.ouyanghaixiong.reflect.ComplexStudent");
        System.out.println(complexStudent);
    }

    @Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ReflectComponent reflect = new ReflectComponent();
        BaseStudent student = reflect.newInstance("com.ouyanghaixiong.reflect.BaseStudent", BaseStudent.class);
        student.setId(1);
        student.setName("ouyanghaixiong");
        student.setAge(99);
        System.out.println(student);
    }

    @Test
    public void invokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ReflectComponent reflect = new ReflectComponent();
        BaseStudent student = new BaseStudent();
        reflect.invokeMethod(student, BaseStudent.class, "setId", 10);
        System.out.println(student);
    }
}

class BaseStudent {
    Integer id;
    String name;
    Integer age;

    public BaseStudent() {
    }

    public BaseStudent(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BaseStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@SuppressWarnings("")
@Deprecated
class ComplexStudent<T> extends Object implements Serializable {
    Integer id;
    String name;
    Integer age;

    public ComplexStudent() {
    }

    public ComplexStudent(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BaseStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexStudent<T> that = (ComplexStudent<T>) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    public T getGenericType() {
        return null;
    }
}
