package kodlama.io.programmingLanguages.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kodlama.io.programmingLanguages.entities.concretes.Languages;

@Repository
public interface LanguagesRepository extends JpaRepository<Languages, Integer> {

}
