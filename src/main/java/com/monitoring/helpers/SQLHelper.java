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

    public Statistics getSummary() {

        List<Metric> metrics = new ArrayList<>();

        String cpuQuery = "SELECT host_name, cpu, avg(per_usr) as per_usr, avg(per_nice) as per_nice, avg(per_sys) as " +
                "per_sys, avg(per_io_wait) as per_io_wait from " + CPU_TABLE + " GROUP BY host_name, cpu";

        metrics.addAll(executeCPUQuery(cpuQuery));

        String memoryQuery = "SELECT host_name, AVG(total) as total, AVG(used) as used, AVG(free) as free FROM "
                + MEMORY_TABLE + " GROUP BY host_name;";

        metrics.addAll(executeMemoryQuery(memoryQuery));

        return new Statistics(metrics);
    }

    public Statistics getStatistics() {


        CPU cpu = new CPU(new CPUFields("all", 2.0, 2.0, 2.0, 2.0), "host1");
        Memory memory = new Memory(new MemoryFields(2.0, 2.0, 2.0), "host1");
        List<Metric> metrics = new ArrayList<>();
        metrics.add(cpu);
        metrics.add(memory);
        return new Statistics(metrics);
    }

    public List<CPU> executeCPUQuery(String sql) {
        Statement statement;
        List<CPU> cpuMetrics = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                cpuMetrics.add( new CPU(
                                new CPUFields(
                                        resultSet.getString("cpu"),
                                        resultSet.getDouble("per_usr"),
                                        resultSet.getDouble("per_nice"),
                                        resultSet.getDouble("per_sys"),
                                        resultSet.getDouble("per_io_wait")
                                ),
                                resultSet.getString("host_name")
                        )
                );

            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO add logging
        }

        return cpuMetrics;
    }

    public List<Memory> executeMemoryQuery(String sql) {
        Statement statement;
        List<Memory> memoryMetrics = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                memoryMetrics.add(
                        new Memory(
                                new MemoryFields(
                                        resultSet.getDouble("total"),
                                        resultSet.getDouble("used"),
                                        resultSet.getDouble("free")

                                ),
                                resultSet.getString("host_name")
                        )
                );

            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO add logging
        }

        return memoryMetrics;
    }
}
