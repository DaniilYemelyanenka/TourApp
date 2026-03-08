package by.sysoev.tourApp.config;

import by.sysoev.tourApp.service.DatabaseBackupService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class BackupScheduler {

    private final DatabaseBackupService backupService;

    // бэкап настроен на раз в неделю в 2 часа ночи
    @Scheduled(cron = "0 0 2 */7 * *")
    public void backupDatabase() throws IOException, InterruptedException {
        backupService.createBackup();
    }
}
