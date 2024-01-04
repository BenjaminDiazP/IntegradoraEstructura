package utez.edu.mx.ExamenD.service;

import utez.edu.mx.ExamenD.model.dto.dtoPerson;
import utez.edu.mx.ExamenD.model.entity.PersonBean;

import java.util.List;

public interface IPerson  {

    PersonBean save(dtoPerson person);

    PersonBean findById(Integer id);

    void delete(PersonBean person);

    List<PersonBean> findAll();
}
