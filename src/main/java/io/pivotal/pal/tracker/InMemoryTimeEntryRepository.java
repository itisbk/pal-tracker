package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long idGenerated = 1L;

    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    @Override
    public TimeEntry create(TimeEntry any) {
            any.setId(idGenerated++);
            timeEntryMap.put(any.getId(), any);
            return any;

    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return timeEntryMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        TimeEntry fromRepo = timeEntryMap.get(eq);
        if(fromRepo != null) {
            fromRepo.setDate(any.getDate());
            fromRepo.setHours(any.getHours());
            fromRepo.setProjectId(any.getProjectId());
            fromRepo.setUserId(any.getUserId());
        }

        return fromRepo;
    }

    @Override
    public void delete(long timeEntryId) {
     timeEntryMap.remove(timeEntryId);
    }

}
