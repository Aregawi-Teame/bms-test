package com.membership.service;

import com.membership.domain.ActivityType;
import com.membership.repository.ActivityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeService{

    @Autowired
    ActivityTypeRepository activityTypeRepository;

    @Override
    public ActivityType save(ActivityType activityType) {
        return activityTypeRepository.save(activityType);
    }

    @Override
    public ActivityType findById(long id) {
        return activityTypeRepository.findById(id).get();
    }

    @Override
    public List<ActivityType> findAll() {
        return activityTypeRepository.findAll();
    }

    @Override
    public ActivityType update(ActivityType activityType, long id) {
        if(activityTypeRepository.findById(id).isPresent()){
         ActivityType existingType = activityTypeRepository.findById(id).get();
         if(activityType.getActivityName()!=null){
             existingType.setActivityName(activityType.getActivityName());
         }
         return activityTypeRepository.save(existingType);
        }else{
            return null;
        }
    }

    @Override
    public void delete(long id) {
        if(activityTypeRepository.findById(id).isPresent()){
            ActivityType activityType = activityTypeRepository.findById(id).get();
            activityTypeRepository.delete(activityType);
        }
    }
}
