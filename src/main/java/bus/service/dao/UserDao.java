package bus.service.dao;

import bus.service.beans.User;
import bus.service.db.ColumnNames;
import bus.service.db.DB;
import bus.service.db.Queries;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 10/04/16.
 */
public class UserDao {

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public Long saveUser(User user) throws SQLException {
        return queryRunner.insert(Queries.INSERT_USER, new ScalarHandler<Long>(), user.getUserName(), user.getPassword(), user.getRole(), user.getEmail());
    }

    public User getUserByUsername(String userName) throws SQLException {
        return queryRunner.query(Queries.SELECT_USER_BY_USERNAME, new ResultSetHandler<User>() {
            public User handle(ResultSet resultSet) throws SQLException {
                User user = null;
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong(ColumnNames.ID_COLUMN));
                    user.setUserName(resultSet.getString(ColumnNames.USER_USERNAME_COLUMN));
                    user.setPassword(resultSet.getString(ColumnNames.USER_PASSWORD_COLUMN));
                    user.setEmail(resultSet.getString(ColumnNames.USER_EMAIL_COLUMN));
                    user.setRole(resultSet.getInt(ColumnNames.USER_ROLE_COLUMN));
                }
                return user;
            }
        }, userName);
    }

    public List<User> getLinkedUsersByRouteNumber(int routeNumber) throws SQLException {
        return queryRunner.query(Queries.SELECT_LINKED_USERS_BY_ROUTE_NUMBER, new ResultSetHandler<List<User>>() {
            public List<User> handle(ResultSet resultSet) throws SQLException {
                List<User> users = new ArrayList<User>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(ColumnNames.ID_COLUMN));
                    user.setUserName(resultSet.getString(ColumnNames.USER_USERNAME_COLUMN));
                    user.setPassword(resultSet.getString(ColumnNames.USER_PASSWORD_COLUMN));
                    user.setEmail(resultSet.getString(ColumnNames.USER_EMAIL_COLUMN));
                    user.setRole(resultSet.getInt(ColumnNames.USER_ROLE_COLUMN));
                    users.add(user);
                }
                return users;
            }
        }, routeNumber);
    }

    public void deleteUserById(long id) throws SQLException {
        queryRunner.update(Queries.DELETE_USER_BY_ID, id);
    }
}
