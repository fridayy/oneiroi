package ninja.harmless.management.service

import ninja.harmless.management.InactivePurgeService
import ninja.harmless.management.UserManagementService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import java.time.LocalDateTime
/**
 * @author bnjm@harmless.ninja - 9/26/16.
 */
@Service
class InactivePurgeServiceImpl implements InactivePurgeService {

    UserManagementService userManagementService
    private final Logger logger = LoggerFactory.getLogger(this.class)

    @Autowired
    InactivePurgeServiceImpl(UserManagementService userManagementService) {
        this.userManagementService = userManagementService
    }

    @Override
    @Scheduled(fixedDelay = 50000L)
    int purge() {
        int removedCounter = 0
        userManagementService.getActiveUser().each {
            def a = LocalDateTime.parse(it.basic.lastLogin)
            if(a.isBefore(LocalDateTime.now().minusDays(1))) {
                userManagementService.remove(it)
                removedCounter++
            }
        }
        logger.debug("Purged {}", removedCounter)
        return removedCounter
    }
}
