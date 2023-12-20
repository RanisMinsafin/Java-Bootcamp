package school21.spring.service.repositories;

public enum Query {
    FIND_USER_BY_ID("select * from service.users where id = ?"),
    FIND_USER_BY_EMAIL("select * from service.users where email = ?"),
    FIND_ALL("select * from service.users"),
    SAVE_USER("insert into service.users(email) values (?)"),
    UPDATE_USER("update service.users set email = ? where id = ?"),
    DELETE_USER("delete from service.users where id = ?");

    private final String QUERY;
    Query(String QUERY){
        this.QUERY = QUERY;
    }
    public String getQuery() {
        return QUERY;
    }
}
