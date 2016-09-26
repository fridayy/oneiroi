package ninja.harmless.management
/**
 * @author bnjm@harmless.ninja - 9/26/16.
 */
interface InactivePurgeService {
    /**
     * Purges a user if he is logged in longer than 24 hours.
     * This method is a @Scheduled method.
     * @return how many users got purged
     */
    int purge()
}