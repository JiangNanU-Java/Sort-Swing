package com.ten.demo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 利用反射，分析打印一个类的全部信息
 *
 * @author wang shihao
 * @version 2017-11-26
 */
public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        try {
            Class c1 = Class.forName(name);
            Class superc1 = c1.getSuperclass();
            String modifiers = Modifier.toString(c1.getModifiers());
            //输出修饰符，例如public
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            //输出类名，例如Sort
            System.out.print("class " + name);
            //输出父类，例如javax.swing.JPanel
            if (superc1 != null && superc1 != Object.class) System.out.print(" extends " + superc1.getName());

            //输出构造方法，函数，域
            System.out.print("\n{\n");
            printConstructors(c1);//构造方法
            System.out.println();
            printMethods(c1);//方法函数
            System.out.println();
            printFields(c1);//域
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);//执行完后退出程序
    }


    /**
     * constructor:构造函数
     *
     * @param c1
     */
    private static void printConstructors(Class c1) {
        //返回包含constructor对象的数组
        Constructor[] constructors = c1.getDeclaredConstructors();
        //遍历每个constructor对象的信息
        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("    ");//缩进
            String modifiers = Modifier.toString(c.getModifiers());
            //输出修饰符
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            //输出方法名
            System.out.print(name + "(");

            //打印参数类型
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 方法
     *
     * @param c1
     */
    private static void printMethods(Class c1) {
        Method[] methods = c1.getMethods();
        //遍历每个方法的信息
        for (Method m : methods) {
            Class returnType = m.getReturnType();
            String name = m.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(m.getModifiers());
            //输出modifier
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            //输出返回值类型+方法名
            System.out.print(returnType.getName() + " " + name + "(");
            Class[] paramTypes = m.getParameterTypes();
            //输出参数信息
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 域
     *
     * @param c1
     */
    private static void printFields(Class c1) {
        Field[] fields = c1.getFields();
        //遍历域的信息
        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(f.getModifiers());
            //输出modifier
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.println(type.getName() + " " + name + ";");
        }
    }

}
