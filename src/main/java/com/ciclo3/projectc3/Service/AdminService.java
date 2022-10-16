package com.ciclo3.projectc3.Service;

import com.ciclo3.projectc3.Entities.Admin;
import com.ciclo3.projectc3.Entities.Category;
import com.ciclo3.projectc3.Entities.Client;
import com.ciclo3.projectc3.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }

    public Optional<Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }

    public Admin save (Admin admin){
        if (admin.getId() == null){
            return adminRepository.save(admin);
        } else {
            Optional<Admin> admin1 = adminRepository.getAdmin(admin.getId());
            if(admin1.isPresent()){
                return adminRepository.save(admin);
            } else {
                return admin;
            }
        }
    }
    public Admin update(Admin admin){
        if(admin.getId()!=null){
            Optional<Admin> admin1= adminRepository.getAdmin(admin.getId());
            if(admin1.isPresent()){
                if(admin.getName()!=null){
                    admin1.get().setName(admin.getName());
                }
                if(admin.getPassword()!=null){
                    admin1.get().setPassword(admin.getPassword());
                }
                adminRepository.save(admin1.get());
                return admin1.get();
            }else{
                return admin;
            }
        }else{
            return admin;
        }
    }

    public boolean deleteAdmin(int id){
        Optional<Admin> admin=adminRepository.getAdmin(id);
        if(!admin.isPresent()) {
           adminRepository.delete(admin.get());
            return true;
        }
        return false;
    }
}
