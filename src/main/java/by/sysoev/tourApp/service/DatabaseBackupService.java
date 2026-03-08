package by.sysoev.tourApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
         process.waitFor();

         //TODO log Baackup created
    }
}
