package com.monitoring.helpers;

import com.monitoring.config.SystemConfig;
import com.monitoring.models.CPU;
import com.monitoring.models.CPUFields;
import com.monitoring.models.Host;
import com.monitoring.models.Memory;
import com.monitoring.models.MemoryFields;
import com.monitoring.models.Metric;
import com.monitoring.models.Statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by venisac
 */
public class SQLHelper {

    private static final String CPU_TABLE = SystemConfig.getStringProperty("cpu_table");
    private static final String MEMORY_TABLE = SystemConfig.getStringProperty("memory_table");
    private static final String HOST_TABLE = SystemConfig.getStringProperty("host_table");

    private final Connection connection;

    public SQLHelper(Connection connection) {
        this.connection = connection;
    }

    public List<Host> getHosts() {

        List<Host> hosts = new ArrayList<>();
        String sql = "SELECT * FROM " + HOST_TABLE;

        Statement statement;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                hosts.add(new Host(resultSet.getString("ip"), resultSet.getString("name")));    
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO add logging
        }    
        
        return hosts;
    }

    public Statistics getAvgStatistics() {

        CPU cpu = new CPU(new CPUFields("all", 2.0, 2.0, 2.0, 2.0));
        Memory memory = new Memory(new MemoryFields(2.0, 2.0, 2.0));
        List<Metric> metrics = new ArrayList<>();
        metrics.add(cpu);
        metrics.add(memory);
        return new Statistics(metrics);
    }

    public Statistics getStatistics() {

        CPU cpu = new CPU(new CPUFields("all", 2.0, 2.0, 2.0, 2.0));
        Memory memory = new Memory(new MemoryFields(2.0, 2.0, 2.0));
        List<Metric> metrics = new ArrayList<>();
        metrics.add(cpu);
        metrics.add(memory);
        return new Statistics(metrics);
    }
}
