package com.hodbenor.project.robust.access.limit.data;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class DataResourceManager {
    private static DataResourceType dbType;

    public static DataResourceType getDataResourceType() {
        return dbType;
    }

    public DataResourceManager() {
        LocalTime now = LocalTime.now();
        if (now.isAfter(LocalTime.of(0, 0)) && now.isBefore(LocalTime.of(17, 0))) {
            dbType = DataResourceType.MYSQL;
        } else {
            dbType = DataResourceType.MOCK;
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateDataResourceToDb() {
        dbType = DataResourceType.MYSQL;
    }

    @Scheduled(cron = "0 19 13 * * ?")
    public void updateDataResourceToMock() {
        dbType = DataResourceType.MOCK;
    }
}
