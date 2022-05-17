package com.membership.controller;

import com.membership.domain.TimeSlot;
import com.membership.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/timeslots")
public class TimeSlotController {
    @Autowired
    TimeSlotService timeSlotService;

    @GetMapping
    public List<TimeSlot> findAll(){
        return  timeSlotService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TimeSlot findById(@PathVariable int id){
        return timeSlotService.findById(id);
    }

    @PostMapping("/location/{locationId}")
    public TimeSlot saveByLocation(@RequestBody TimeSlot timeSlot, @RequestParam(name = "locationId") long locationId){
        return timeSlotService.save(timeSlot, locationId);
    }
    @PostMapping
    public TimeSlot addNewTimeSlot(@RequestBody TimeSlot timeSlot){
        return timeSlotService.addNew(timeSlot);
    }
    @PutMapping(value = "/{id}")
    public TimeSlot updateById(@RequestBody TimeSlot timeSlot, @PathVariable long id){
        return timeSlotService.update(timeSlot, id);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable long id){
        timeSlotService.deleteById(id);
    }
}
