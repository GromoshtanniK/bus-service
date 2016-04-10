package bus.service.dao;

import bus.service.beans.User;
import bus.service.db.DB;
import bus.service.db.Queries;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * Created by andrey on 10/04/16.
 */
public class UserDao {

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public Long saveUser(User user) throws SQLException {
        return queryRunner.insert(Queries.INSERT_USER, new ScalarHandler<Long>(), user.getUserName(), user.getPassword(), user.getRole(), user.getEmail());
    }
}
