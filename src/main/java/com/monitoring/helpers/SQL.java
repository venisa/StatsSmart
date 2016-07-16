package com.monitoring.helpers;

import com.monitoring.models.CPU;
import com.monitoring.models.CPUFields;
import com.monitoring.models.Host;
import com.monitoring.models.Memory;
import com.monitoring.models.MemoryFields;
import com.monitoring.models.Metric;
import com.monitoring.models.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venisac
 */
public class SQL {

    public static List<Host> getHosts() {
        Host host1 = new Host("host1", "127.0.0");
        Host host2 = new Host("host2", "127.0.0");

        List<Host> hosts = new ArrayList<>();
        hosts.add(host1);
        hosts.add(host2);

        return hosts;
    }

    public static Statistics getAvgStatistics() {

        CPU cpu = new CPU(new CPUFields("all", 2.0, 2.0, 2.0, 2.0));
        Memory memory = new Memory(new MemoryFields(2.0, 2.0, 2.0));
        List<Metric> metrics = new ArrayList<>();
        metrics.add(cpu);
        metrics.add(memory);
        return new Statistics(metrics);
    }
}
