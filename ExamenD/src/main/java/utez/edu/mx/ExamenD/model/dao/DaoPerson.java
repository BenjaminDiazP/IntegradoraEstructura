package utez.edu.mx.ExamenD.model.dao;

import org.springframework.data.repository.CrudRepository;
import utez.edu.mx.ExamenD.model.entity.PersonBean;

public interface DaoPerson   extends CrudRepository<PersonBean, Integer> {
}
