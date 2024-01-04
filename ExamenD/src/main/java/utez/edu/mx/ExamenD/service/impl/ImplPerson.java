package utez.edu.mx.ExamenD.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utez.edu.mx.ExamenD.model.dao.DaoPerson;
import utez.edu.mx.ExamenD.model.dto.dtoPerson;
import utez.edu.mx.ExamenD.model.entity.PersonBean;
import utez.edu.mx.ExamenD.service.IPerson;

import java.util.List;

@Service
public class ImplPerson implements IPerson {

    @Autowired
    private DaoPerson daoPerson;

    @Transactional
    @Override
    public PersonBean save(dtoPerson dtoPerson) {
        PersonBean person = PersonBean.builder()
                .idPerson(dtoPerson.getId())
                .name((dtoPerson.getName()))
                .lastName(dtoPerson.getLastName())
                .curp(dtoPerson.getCurp())
                .fechanacimiento(dtoPerson.getFechanacimiento())
                .build();
        return daoPerson.save(person);
    }


    @Override
    public PersonBean findById(Integer id) {
        return null;
    }

    @Override
    public void delete(PersonBean person) {

    }

    @Override
    public List<PersonBean> findAll() {
        return null;
    }
}
