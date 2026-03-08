package by.sysoev.tourApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DatabaseBackupService {

    @Value("${backup.path}")
    private String backupPath;

    public void createBackup() throws IOException, InterruptedException {
         String filename = "backup_" + System.currentTimeMillis() + ".sql";

         ProcessBuilder processBuilder = new ProcessBuilder(
                 "pg_dump",
                 "-U","user",
                 "-d", "tourDB",
                 "-f",backupPath + "/"+ filename
         );

         processBuilder.environment().put("PGPASSWORD", "user");

         Process process = processBuilder.start();
         log.info("Starting backup database  at");
         process.waitFor();

         log.info("Successful creating backup to database");
    }
}
