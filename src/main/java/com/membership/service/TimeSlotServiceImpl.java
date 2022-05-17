package com.membership.service;

import com.membership.domain.Location;
import com.membership.domain.TimeSlot;
import com.membership.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotServiceImpl implements TimeSlotService{
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    LocationService locationService;

    @Override
    public TimeSlot findById(long id) {
        return timeSlotRepository.getById(id);
    }

    @Override
    public List<TimeSlot> findAll() {
        return timeSlotRepository.findAll();
    }

    @Override
    public TimeSlot save(TimeSlot timeSlot, long locationId) {
        Location location = locationService.findById(locationId);
        location.getTimeSlots().add(timeSlot);
        return timeSlotRepository.save(timeSlot);
    }

    @Override
    public TimeSlot update(TimeSlot timeslot, long id) {
        if (timeSlotRepository.findById(id).isPresent()){
            TimeSlot existingTimeSlot = timeSlotRepository.findById(id).get();

            if(timeslot.getStartTime()!=null) {
                existingTimeSlot.setStartTime(timeslot.getStartTime());
            }
            if(timeslot.getEndTime()!=null) {
                existingTimeSlot.setEndTime(timeslot.getEndTime());
            }
            if(timeslot.getDayOfWeek()!=null){
                existingTimeSlot.setDayOfWeek(timeslot.getDayOfWeek());
            }
            if(timeslot.getActivityType()!=null){
                existingTimeSlot.setActivityType(timeslot.getActivityType());
            }

            return timeSlotRepository.save(existingTimeSlot);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        TimeSlot ts = timeSlotRepository.getById(id);
        timeSlotRepository.delete(ts);
    }

    @Override
    public TimeSlot addNew(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }


}
