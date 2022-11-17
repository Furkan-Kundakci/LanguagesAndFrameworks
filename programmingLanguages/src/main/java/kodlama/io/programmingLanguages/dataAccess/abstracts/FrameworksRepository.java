package kodlama.io.programmingLanguages.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlama.io.programmingLanguages.entities.concretes.Frameworks;

@Repository
public interface FrameworksRepository extends JpaRepository<Frameworks, Integer> {

}
