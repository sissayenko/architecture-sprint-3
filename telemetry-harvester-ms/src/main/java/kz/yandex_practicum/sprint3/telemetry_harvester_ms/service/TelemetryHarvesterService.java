package kz.yandex_practicum.sprint3.telemetry_harvester_ms.service;

import jakarta.annotation.PostConstruct;
import static java.lang.Math.random;
import java.sql.PreparedStatement;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import kz.yandex_practicum.sprint3.telemetry_harvester_ms.mappers.DevicesListRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author SV
 */
@Service
public class TelemetryHarvesterService {

    @Value("${smarthome.device_management.baseUrl}")
    private String deviceBaseUrl = "http://localhost";

    private static final Logger logger = LoggerFactory.getLogger(TelemetryHarvesterService.class);
    
    private final String[] units = {"celsius", "lumen"};

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void onStartup() {
        getDevices();
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void onSchedule() {
        getDevices();
    }

    public void getDevices() {
        RestClient restClient = RestClient.builder().baseUrl(this.deviceBaseUrl).build();
        List<DeviceResponse> ldr = restClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("/devices/all")
                .build()
                )
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        this.prepareQueue(ldr);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void runDataHarvesting() {
        final String sql = "select distinct device_id, device_type from telemetry.queue";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm:ss.SSS'+05:00'");
        logger.info(formatter.format(OffsetDateTime.now(ZoneId.of("UTC+5"))));
        
        List<DeviceResponse> devices = jdbcTemplate.queryForObject(sql, new DevicesListRowMapper());
        
        devices.forEach(d -> this.getTelemetry(d.getId()));
        
    }

    //public boolean HandleDeviceType(DeviceType deviceType
    private void prepareQueue(List<DeviceResponse> ldr) {
        final String deleteSql = "delete from telemetry.queue";
        final String insertSql = "insert into telemetry.queue (device_id, device_type) values(?, ?)";

        jdbcTemplate.update(deleteSql);

        jdbcTemplate.batchUpdate(insertSql, ldr, 50,
                (PreparedStatement ps, DeviceResponse dr) -> {
                    ps.setString(1, dr.getId());
                    ps.setString(2, dr.getDeviceType().toString());
                }
        );

    }

    private void getTelemetry(String deviceId) {
        final String insertSql = "insert into telemetry.telemetry (device_id, value, unit, sensor) values (?, ?, ?, ?)";
        
        Random rand = new Random();        
        String value = String.valueOf(rand.nextInt(100) + 1);
        
        String sensor = "Sensor# " + String.valueOf(rand.nextInt(5) + 1);
        
        String unit = this.units[rand.nextInt(2)];
        
        jdbcTemplate.update(insertSql, deviceId, value, unit, sensor);
    }
}
