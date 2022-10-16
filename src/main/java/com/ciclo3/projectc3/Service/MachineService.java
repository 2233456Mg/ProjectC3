package com.ciclo3.projectc3.Service;

import com.ciclo3.projectc3.Entities.Category;
import com.ciclo3.projectc3.Entities.Machine;
import com.ciclo3.projectc3.Repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAll(){
        return machineRepository.getAll();
    }

    public Optional<Machine> getMachine(int id){
        return machineRepository.getMachine(id);
    }

    public Machine save(Machine machine){
        if (machine.getId() == null){
            return machineRepository.save(machine);
        } else {
            Optional<Machine> machine1 = machineRepository.getMachine(machine.getId());
            if(machine1.isPresent()){
                return machine;
            } else {
                return machineRepository.save(machine);
            }
        }
    }

    public Machine update(Machine machine){
        if(machine.getId()!=null){
            Optional<Machine> machine1 = machineRepository.getMachine(machine.getId());
            if(machine1.isPresent()) {
                if (machine.getName() != null) {
                    machine1.get().setName(machine.getName());
                }
                if (machine.getBrand() != null) {
                    machine1.get().setBrand(machine.getBrand());
                }
                if (machine.getYear() != null) {
                    machine1.get().setYear(machine.getYear());
                }
                if (machine.getDescription() != null) {
                    machine1.get().setDescription(machine.getDescription());
                }
                if(machine.getCategory()!=null){
                    machine1.get().setCategory(machine.getCategory());
                }

                machineRepository.save(machine1.get());
                return machine1.get();

            }else {
                return machine;
            }
        }else return machine;
    }
    public boolean deleteMachine(int id){
        Optional<Machine> machine=machineRepository.getMachine(id);
        if(!machine.isPresent()) {
            machineRepository.delete(machine.get());
            return true;
        }
        return false;
    }
}
