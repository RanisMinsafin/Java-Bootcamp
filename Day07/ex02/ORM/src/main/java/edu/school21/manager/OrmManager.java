package edu.school21.manager;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;
import edu.school21.converter.TypeConverter;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrmManager {
    private final String QUERY_DROP_TABLE = "drop table if exists %s cascade";
    private final String QUERY_CREATE_TABLE = "create table if not exists %s (%s)";
    private final String QUERY_SAVE = "insert into %s (%s) values (%s)";
    private final String QUERY_UPDATE = "update %s set %s where id = ?";
    private final String QUERY_FIND_BY_ID = "select * from %s where id = ?";
    private Connection connection;

    public OrmManager(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize OrmManager", e);
        }
    }

    public void dropTable(Class<?> clazz) {
        String tableName = clazz.getAnnotation(OrmEntity.class).table();
        String dropQuery = String.format(QUERY_DROP_TABLE, tableName);
        try (PreparedStatement statement = connection.prepareStatement(dropQuery)) {
            statement.execute();
            System.out.println("Executed query: " + dropQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Error dropping table", e);
        }
    }

    private List<String> getTableDefinition(Field[] fields) {
        List<String> definitions = new ArrayList<>(fields.length);
        for (Field field : fields) {
            OrmColumn column = field.getAnnotation(OrmColumn.class);

            if (field.isAnnotationPresent(OrmColumnId.class)) {
                definitions.add(field.getName() + " serial primary key");
            } else if (column != null) {
                String name = column.name();
                String type = TypeConverter.getSqlType(field.getType());
                StringBuilder definition = new StringBuilder(name + " " + type);

                if ("varchar".equalsIgnoreCase(type)) {
                    definition.append("(").append(column.length()).append(")");
                }
                definitions.add(definition.toString());
            }
        }
        return definitions;
    }

    public void createTable(Class<?> clazz) {
        dropTable(clazz);
        String tableName = clazz.getAnnotation(OrmEntity.class).table();
        Field[] fields = clazz.getDeclaredFields();

        String tableDefinition = String.join(", ", getTableDefinition(fields));
        String createQuery = String.format(QUERY_CREATE_TABLE, tableName, tableDefinition);
        try (PreparedStatement statement = connection.prepareStatement(createQuery)) {
            statement.execute();
            System.out.println("Executed query: " + createQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating table", e);
        }
    }

    private List<String> getColumnNames(Field[] fields) {
        List<String> columnNames = new ArrayList<>(fields.length);
        for (Field field : fields) {
            OrmColumn column = field.getAnnotation(OrmColumn.class);
            if (column != null) {
                String name = column.name();
                columnNames.add(name);
            }
        }
        return columnNames;
    }

    public void save(Object entity) {
        Class<?> entityClass = entity.getClass();
        if (entityClass.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity ormEntity = entityClass.getAnnotation(OrmEntity.class);
            Field[] fields = entityClass.getDeclaredFields();

            List<String> columnNamesList = getColumnNames(fields);
            String columnNames = String.join(", ", columnNamesList);

            String placeholders = Stream.generate(() -> "?")
                    .limit(columnNamesList.size())
                    .collect(Collectors.joining(", "));

            String tableName = ormEntity.table();
            String query = String.format(QUERY_SAVE, tableName, columnNames, placeholders);

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                int parameterIndex = 1;
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(OrmColumnId.class)) {
                        field.setAccessible(true);
                        statement.setObject(parameterIndex++, field.get(entity));
                    }
                }

                statement.execute();
                System.out.println("Executed query: " + query);
            } catch (SQLException | IllegalAccessException e) {
                throw new RuntimeException("Error saving object", e);
            }
        }
    }


    private String getFieldDefinition(Field field, Object entity) throws IllegalAccessException {
        StringBuilder fieldDefinition = new StringBuilder();
        if (field.isAnnotationPresent(OrmColumn.class)) {
            String name = field.getAnnotation(OrmColumn.class).name() + " = ";
            StringBuilder value = new StringBuilder();
            Object fieldValue = field.get(entity);

            if (field.getType() == String.class) {
                value.append("'").append(fieldValue).append("'");
            } else {
                value.append(fieldValue);
            }

            fieldDefinition.append(name).append(value);
        }
        return fieldDefinition.toString();
    }

    public void update(Object entity) {
        try {
            OrmEntity ormEntity = entity.getClass().getAnnotation(OrmEntity.class);

            Field[] fields = entity.getClass().getDeclaredFields();
            List<String> definitions = new ArrayList<>(fields.length);
            String idField = null;

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(OrmColumn.class)) {
                    String fieldDefinition = getFieldDefinition(field, entity);
                    definitions.add(fieldDefinition);
                } else if (field.isAnnotationPresent(OrmColumnId.class)) {
                    Object idObject = field.get(entity);
                    idField = idObject.toString();
                }
            }

            String setClause = String.join(", ", definitions);
            String query = String.format(QUERY_UPDATE, ormEntity.table(), setClause);

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, Long.parseLong(idField));
                statement.executeUpdate();
                System.out.println("Executed query: " + query);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating object: SQLException", e);
        } catch (IllegalAccessException e){
            throw new RuntimeException("Error updating object: IllegalAccessException", e);
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        OrmEntity ormEntity = aClass.getAnnotation(OrmEntity.class);
        try {
            T object = aClass.getConstructor().newInstance();
            String query = String.format(QUERY_FIND_BY_ID, ormEntity.table());
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                System.out.println("Executed query: " + query);
                if (resultSet.next()) {
                    fillObjectFields(resultSet, object);
                    return object;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding object", e);
        }
        return null;
    }

    private void fillObjectFields(ResultSet resultSet, Object object) throws SQLException, IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(OrmColumn.class)) {
                String columnName = field.getAnnotation(OrmColumn.class).name();
                Object value = resultSet.getObject(columnName);
                field.set(object, value);
            } else if (field.isAnnotationPresent(OrmColumnId.class)) {
                Long value = resultSet.getLong("id");
                field.set(object, value);
            }
        }
    }

}
