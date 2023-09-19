package med.voll.api.paciente;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

public interface PacienteRepository extends JpaRepository<PacienteJPA, Long> {
    Page<PacienteJPA> findAllByAtivoTrue(Pageable paginacao);
}
