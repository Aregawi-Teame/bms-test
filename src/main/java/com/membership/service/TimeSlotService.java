package com.membership.service;

import com.membership.domain.TimeSlot;

import java.sql.Time;
import java.util.List;

public interface TimeSlotService {
    public TimeSlot findById(long id);
    public List<TimeSlot> findAll();
    public TimeSlot save(TimeSlot timeSlot, long locationId);
    public TimeSlot update(TimeSlot timeslot, long id);
    public void deleteById(long id);

    public TimeSlot addNew(TimeSlot timeSlot);
}
