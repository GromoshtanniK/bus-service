package bus.service.dao;

import bus.service.SQL;
import bus.service.beans.BusStop;
import bus.service.db.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusStopDao {

    private static final String ID_COLUMN = "id";
    private static final String ALTITUDE_COLUMN = "altitude";
    private static final String LATITUDE_COLUMN = "latitude";

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void createBusStop(BusStop busStop) throws SQLException {
        queryRunner.update(SQL.INSERT_BUS_STOP, busStop.getAltitude(), busStop.getLatitude());
    }

    public List<BusStop> getAllBusStops() throws SQLException {
        return queryRunner.query(SQL.SELECT_ALL_BUS_STOPS, new ResultSetHandler<List<BusStop>>() {
            public List<BusStop> handle(ResultSet rs) throws SQLException {
                List<BusStop> busStops = new ArrayList<BusStop>();
                while (rs.next()) {
                    BusStop busStop = new BusStop();
                    busStop.setId(rs.getLong(ID_COLUMN));
                    busStop.setAltitude(rs.getDouble(ALTITUDE_COLUMN));
                    busStop.setLatitude(rs.getDouble(LATITUDE_COLUMN));
                    busStops.add(busStop);
                }
                return busStops;
            }
        });
    }

    public BusStop getBusStopById(long id) throws SQLException {
        return queryRunner.query(SQL.SELECT_BUS_STOP_BY_ID, new ResultSetHandler<BusStop>() {
            public BusStop handle(ResultSet rs) throws SQLException {
                BusStop busStop = null;
                while (rs.next()) {
                    busStop = new BusStop();
                    busStop.setId(rs.getLong(ID_COLUMN));
                    busStop.setAltitude(rs.getDouble(ALTITUDE_COLUMN));
                    busStop.setLatitude(rs.getDouble(LATITUDE_COLUMN));
                }
                return busStop;
            }
        }, id);
    }

    public void deleteBusStopById(long id) throws SQLException {
        queryRunner.update(SQL.DELETE_BUS_STOP_BY_ID, id);
    }

    public void deleteBusStop(BusStop busStop) throws SQLException {
        deleteBusStopById(busStop.getId());
    }

}
