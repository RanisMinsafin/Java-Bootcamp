package menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Menu {
    private final String PACKAGE_NAME = "model.";
    private Scanner scanner = new Scanner(System.in);
    private Set<Class<?>> classSet;
    private String className;
    private Object object;

    public Menu(Class<?>... classes) {
        this.classSet = new HashSet<>(Arrays.asList(classes));
    }

    public void start() {
        printClasses();
        chooseClass();
        try {
            printClass();
            createObject();
            updateObject();
            callMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        scanner.close();
    }

    private void printClasses() {
        System.out.println("Classes:");
        for (Class<?> c : classSet) {
            System.out.println("\t- " + c.getSimpleName());
        }
        System.out.println("---------------------");
    }

    private void chooseClass() {
        System.out.println("Enter class name:");
        this.className = scanner.next();
        System.out.println("---------------------");
    }

    private void printClass() throws ClassNotFoundException {
        String fullClassName = PACKAGE_NAME + className;
        Class<?> c = Class.forName(fullClassName);
        if (classSet.contains(c)) {
            printFields(c.getDeclaredFields());
            printMethods(c.getDeclaredMethods());
        }
        System.out.println("---------------------");
    }

    private void printFields(Field[] fields) {
        System.out.println("fields:");
        for (Field f : fields) {
            String fieldName = f.getName();
            String fieldType = f.getType().getSimpleName();
            System.out.println("\t- " + fieldType + " " + fieldName);
        }
    }

    private void printMethods(Method[] methods) {
        System.out.println("methods:");
        for (Method m : methods) {
            String methodName = m.getName();
            String methodType = m.getReturnType().getSimpleName();
            Class<?>[] parameterTypes = m.getParameterTypes();
            StringBuilder paramTypeString = new StringBuilder();
            for (int i = 0; i < parameterTypes.length; i++) {
                paramTypeString.append(parameterTypes[i].getSimpleName());
                if (i < parameterTypes.length - 1) {
                    paramTypeString.append(", ");
                }
            }
            System.out.println("\t- " + methodType + " " + methodName + "(" + paramTypeString + ")");
        }
    }

    private int returnClassType(Class<?> c) {
        if (c.equals(int.class)) {
            return 0;
        } else if (c.equals(double.class)) {
            return 1;
        } else if (c.equals(long.class)) {
            return 2;
        } else if (c.equals(boolean.class)) {
            return 3;
        } else if (c.equals(String.class)) {
            return 4;
        }
        return 5;
    }

    private Object[] getMethodParameters(Class<?> c, Constructor<?> paramConstructor) {
        Class<?>[] parameterTypes = paramConstructor.getParameterTypes();
        Object[] parameterValues = new Object[parameterTypes.length];

        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName() + ":");
            switch (returnClassType(parameterTypes[i])) {
                case 0:
                    parameterValues[i] = scanner.nextInt();
                    break;
                case 1:
                    parameterValues[i] = scanner.nextDouble();
                    break;
                case 2:
                    parameterValues[i] = scanner.nextLong();
                    break;
                case 3:
                    parameterValues[i] = scanner.nextBoolean();
                    break;
                case 4:
                    parameterValues[i] = scanner.next();
                    break;
                default:
                    System.out.println("Error: incorrect parameter type");
            }
        }
        return parameterValues;
    }

    private void createObject() throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String fullClassName = PACKAGE_NAME + className;
        Class<?> c = Class.forName(fullClassName);
        System.out.println("Let’s create an object.");
        Constructor<?>[] constructors = c.getDeclaredConstructors();
        Constructor<?> paramConstructor = Arrays.stream(constructors).
                filter(constructor -> constructor.getParameterCount() > 0).
                findFirst().orElseThrow(() -> new IllegalArgumentException("No parameterless constructor found"));

        Object[] parameterValues = getMethodParameters(c, paramConstructor);
        object = paramConstructor.newInstance(parameterValues);

        System.out.println("Object created: " + object);
        System.out.println("---------------------");
    }

    private void updateObject() throws NoSuchFieldException, IllegalAccessException {
        System.out.println("Enter name of the field for changing:");
        String fieldName = scanner.next();
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        switch (returnClassType(field.getType())) {
            case 0:
                System.out.println("Enter int value:");
                field.set(object, scanner.nextInt());
                break;
            case 1:
                System.out.println("Enter double value:");
                field.set(object, scanner.nextDouble());
                break;
            case 2:
                System.out.println("Enter long value:");
                field.set(object, scanner.nextLong());
                break;
            case 3:
                System.out.println("Enter boolean value:");
                field.set(object, scanner.nextBoolean());
                break;
            case 4:
                System.out.println("Enter String value:");
                field.set(object, scanner.next());
                break;
            default:
                System.out.println("Error: incorrect parameter type");
        }
        System.out.println("Object updated:" + object);
        System.out.println("---------------------");
    }

    private Method findMethod(Object object, String methodName, String[] parameterValues) throws NoSuchMethodException {
        Class<?>[] parameterTypes = Arrays.stream(parameterValues)
                .map(String::trim)
                .map(this::getParameterType)
                .toArray(Class[]::new);

        return object.getClass().getDeclaredMethod(methodName, parameterTypes);
    }

    private Object invokeMethod(Method method, String[] parameterValues) throws IllegalAccessException, InvocationTargetException {
        Object[] convertedValues = Arrays.stream(parameterValues)
                .map(String::trim)
                .map(this::convertParameterValue)
                .toArray();

        method.setAccessible(true);

        System.out.println("Method returned:");
        if (method.getParameterCount() > 0) {
            return method.invoke(object, convertedValues);
        } else {
            return method.invoke(object);
        }
    }

    private Class<?> getParameterType(String parameterValue) {
        switch (parameterValue) {
            case "int":
                return int.class;
            case "double":
                return double.class;
            case "long":
                return long.class;
            case "boolean":
                return boolean.class;
            default:
                return String.class;
        }
    }

    private Object convertParameterValue(String parameterValue) {
        switch (parameterValue) {
            case "int":
                System.out.println("Enter int value:");
                return scanner.nextInt();
            case "double":
                System.out.println("Enter double value:");
                return scanner.nextDouble();
            case "long":
                System.out.println("Enter long value:");
                return scanner.nextLong();
            case "boolean":
                System.out.println("Enter boolean value:");
                return scanner.nextBoolean();
            default:
                System.out.println("Enter String value:");
                return scanner.next();
        }
    }

    private void invokeNoParametersMethod(String input) {
        try {
            Method method = object.getClass().getDeclaredMethod(input);
            method.setAccessible(true);
            System.out.println("Method returned:");
            if (Objects.equals(method.getReturnType(), void.class)) {
                method.invoke(object);
            } else {
                System.out.println(method.invoke(object));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void callMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of the method for call:");
        String input = scanner.next().trim();

        int indexOfOpenParenthesis = input.indexOf('(');
        int indexOfCloseParenthesis = input.indexOf(')');

        if (indexOfOpenParenthesis != -1 && indexOfCloseParenthesis != -1
                && indexOfCloseParenthesis > indexOfOpenParenthesis) {
            String methodName = input.substring(0, indexOfOpenParenthesis).trim();
            String parametersString = input.substring(indexOfOpenParenthesis + 1, indexOfCloseParenthesis);
            String[] parameterValues = parametersString.split(",");

            Method method = findMethod(object, methodName, parameterValues);
            System.out.println(invokeMethod(method, parameterValues));
        } else {
            invokeNoParametersMethod(input);
        }
    }
}
