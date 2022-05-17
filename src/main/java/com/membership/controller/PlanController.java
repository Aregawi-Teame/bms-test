package com.membership.controller;

import com.membership.domain.Location;
import com.membership.domain.Plan;
import com.membership.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/plans")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping
    public List<Plan> getAllPlans(){
        return planService.findAll();
    }

    @GetMapping("/{id}")
    public Plan getAPlan(@PathVariable(name = "id") Long id){
        return planService.findById(id);
    }

    @PostMapping
    public Plan addAPlan(@RequestBody Plan plan){
        return planService.save(plan);
    }

    @PutMapping("/{id}")
    public Plan updateAPlan(@RequestBody Plan plan, @PathVariable(name = "id") Long id){
        return planService.update(plan, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAPlan(@PathVariable(name = "id") Long id){
        planService.deleteById(id);
    }

    @GetMapping("/{id}/locations")
    public Set<Location> getAllPlanLocations(@PathVariable(name = "id") Long id){
        return planService.findAllPlanLocations(id);
    }

}
