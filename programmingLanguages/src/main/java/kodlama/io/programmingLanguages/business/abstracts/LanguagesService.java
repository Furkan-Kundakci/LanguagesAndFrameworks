package kodlama.io.programmingLanguages.business.abstracts;

import java.util.List;

import kodlama.io.programmingLanguages.business.requests.CreateLanguageRequest;
import kodlama.io.programmingLanguages.business.requests.DeleteLanguageRequest;
import kodlama.io.programmingLanguages.business.requests.UpdateLanguageRequest;
import kodlama.io.programmingLanguages.business.responses.GetAllLanguageResponse;
import kodlama.io.programmingLanguages.business.responses.GetByIdLanguageResponse;

public interface LanguagesService {

	List<GetAllLanguageResponse> getAll();

	GetByIdLanguageResponse getById(int id);

	void update(UpdateLanguageRequest updateLanguageRequest) throws Exception;

	void add(CreateLanguageRequest createLanguageRequest) throws Exception;

	void delete(DeleteLanguageRequest deleteLanguageRequest) throws Exception;

}
