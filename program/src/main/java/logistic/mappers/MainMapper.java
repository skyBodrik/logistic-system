package logistic.mappers;

import org.sql2o.Sql2o;

public class MainMapper {
    protected static Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/logistic_db?useUnicode=yes&characterEncoding=UTF-8", "root", "sky211993");
}