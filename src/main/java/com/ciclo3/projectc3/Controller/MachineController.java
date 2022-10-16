package com.ciclo3.projectc3.Controller;

import com.ciclo3.projectc3.Entities.Category;
import com.ciclo3.projectc3.Entities.Machine;
import com.ciclo3.projectc3.Service.MachineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Machine")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping("/all")
    public List<Machine> getAll(){
        return machineService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Machine> getMachine(@PathVariable("id") int id){
        return machineService.getMachine(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine save (@RequestBody Machine machine){
        return machineService.save(machine);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine update (@RequestBody Machine machine){
        return machineService.update(machine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMachine(@PathVariable("id") int id) {
        return machineService.deleteMachine(id);
    }

}
