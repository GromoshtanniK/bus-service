package bus.service.dao;

import bus.service.beans.StopTime;
import bus.service.db.DB;
import bus.service.db.Queries;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;


public class StopTimeDao {

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void createStopTime(StopTime stopTime) throws SQLException {
        queryRunner.update(Queries.INSERT_STOP_TIME, stopTime.getHours(), stopTime.getMinutes(), stopTime.getRouteStopId());
    }

    private void deleteStopTime(StopTime stopTime) {
        //todo
    }

    private void deleteStopTimeByID(long id) {
        //todo
    }

    private void updateStopTime(StopTime stopTime) {
        //todo
    }

    private StopTime getStopTimeById(long id) {
        //todo
        return null;
    }

    private List<StopTime> getStopTimesByRouteStopId(long id) {
        //todo
        return null;
    }
}
