package utez.edu.mx.ExamenD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.ExamenD.model.dto.dtoPerson;
import utez.edu.mx.ExamenD.model.entity.PersonBean;
import utez.edu.mx.ExamenD.service.IPerson;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Person")
public class ControllerPerson {

    @Autowired
    private IPerson personService;

    @PostMapping("/post")
    public dtoPerson create(@RequestBody dtoPerson dtoperson) {
        PersonBean personsave = personService.save(dtoperson);
        return dtoperson.builder()
                .id(personsave.getIdPerson())
                .name(personsave.getName())
                .lastName(personsave.getLastName())
                .curp(personsave.getCurp())
                .fechanacimiento(personsave.getFechanacimiento())
                .build();
    }

    @PutMapping("/{id}")
    public dtoPerson update(@RequestBody dtoPerson dtoperson) {
        PersonBean personUpdate = personService.save(dtoperson);
        return dtoperson.builder()
                .id(personUpdate.getIdPerson())
                .name(personUpdate.getName())
                .lastName(personUpdate.getLastName())
                .curp(personUpdate.getCurp())
                .fechanacimiento(personUpdate.getFechanacimiento())
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        PersonBean person = personService.findById(id);
        personService.delete(person);
    }


    @GetMapping("/{id}")
    public PersonBean showById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @GetMapping("/all")
    public List<dtoPerson> showAll() {
        List<PersonBean> persons = personService.findAll();
        return persons.stream()
                .map(person -> new dtoPerson(
                        person.getIdPerson(),
                        person.getName(),
                        person.getLastName(),
                        person.getCurp(),
                        person.getFechanacimiento()))
                .collect(Collectors.toList());
    }


}
