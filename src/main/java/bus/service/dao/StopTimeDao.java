package bus.service.dao;

import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
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


public class StopTimeDao {

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void addStopTime(StopTime stopTime) throws SQLException {
        long stopTimeId = queryRunner.insert(Queries.INSERT_STOP_TIME, new ScalarHandler<Long>(), stopTime.getHours(), stopTime.getMinutes(), stopTime.getRouteStopId());
        stopTime.setId(stopTimeId);
    }

    public void updateStopTime(StopTime stopTime) throws SQLException {
        queryRunner.update(Queries.UPDATE_STOP_TIME, stopTime.getHours(), stopTime.getMinutes(), stopTime.getId());
    }

    public List<StopTime> getStopTimesByRouteStopId(long id) throws SQLException {
        return queryRunner.query(Queries.SELECT_STOP_TIMES_BY_ROUTE_STOP, new ResultSetHandler<List<StopTime>>() {
            public List<StopTime> handle(ResultSet resultSet) throws SQLException {
                List<StopTime> stopTimes = new ArrayList<StopTime>();
                while (resultSet.next()) {
                    StopTime stopTime = new StopTime();
                    stopTime.setId(resultSet.getInt(ColumnNames.ID_COLUMN));
                    stopTime.setHours(resultSet.getInt(ColumnNames.STOP_TIME_HOURS_COLUMN));
                    stopTime.setMinutes(resultSet.getInt(ColumnNames.STOP_TIME_MINUTES_COLUMN));
                    stopTime.setRouteStopId(resultSet.getLong(ColumnNames.ROUTE_STOP_ID_COLUMN));
                    stopTimes.add(stopTime);
                }
                return stopTimes;
            }
        }, id);
    }

    public List<StopTime> getStopTimesByRouteStop(RouteStop routeStop) throws SQLException {
        return getStopTimesByRouteStopId(routeStop.getId());
    }

    public void deleteStopTime(StopTime stopTime) throws SQLException {
        queryRunner.update(Queries.DELETE_STOP_TIME_BY_ID, stopTime.getId());
    }
}
