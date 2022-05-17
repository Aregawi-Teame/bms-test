package com.membership.service;

import com.membership.domain.Role;
import com.membership.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Long id, Role role) {
        Role existingRole = findById(id);
        Role updateResponse = null;
        if(existingRole != null){
            updateResponse = roleRepository.save(role);
        }
        return updateResponse;
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
