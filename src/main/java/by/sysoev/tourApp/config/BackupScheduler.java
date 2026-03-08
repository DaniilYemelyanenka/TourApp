package by.sysoev.tourApp.config;

import by.sysoev.tourApp.service.DatabaseBackupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class BackupScheduler {

    private final DatabaseBackupService backupService;

    // бэкап настроен на раз в неделю в 2 часа ночи
    @Scheduled(cron = "0 0 2 */7 * *")
    public void backupDatabase() throws IOException, InterruptedException {
        log.info("Backup scheduler starts");
        backupService.createBackup();
    }
}
