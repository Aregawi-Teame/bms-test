package com.membership.service;

import com.membership.domain.Location;
import com.membership.domain.Plan;
import com.membership.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PlanServiceImpl implements PlanService{
    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan findById(Long id) {
        return planRepository.getById(id);
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan update(Plan plan, Long id) {
        Plan existingPlan = findById(id);
        Plan updateResponse = null;
        if(existingPlan != null){
            updateResponse = planRepository.save(plan);
        }
        return updateResponse;
    }

    @Override
    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public Set<Location> findAllPlanLocations(Long id) {
        return planRepository.getById(id).getLocations();
    }
}
