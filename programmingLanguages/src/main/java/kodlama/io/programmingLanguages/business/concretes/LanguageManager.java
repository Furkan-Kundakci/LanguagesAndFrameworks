package kodlama.io.programmingLanguages.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import kodlama.io.programmingLanguages.business.abstracts.LanguagesService;
import kodlama.io.programmingLanguages.business.requests.CreateLanguageRequest;
import kodlama.io.programmingLanguages.business.requests.DeleteFrameworkRequest;
import kodlama.io.programmingLanguages.business.requests.DeleteLanguageRequest;
import kodlama.io.programmingLanguages.business.requests.UpdateLanguageRequest;
import kodlama.io.programmingLanguages.business.responses.GetAllLanguageResponse;
import kodlama.io.programmingLanguages.business.responses.GetByIdLanguageResponse;
import kodlama.io.programmingLanguages.dataAccess.abstracts.FrameworksRepository;
import kodlama.io.programmingLanguages.dataAccess.abstracts.LanguagesRepository;
import kodlama.io.programmingLanguages.entities.concretes.Frameworks;
import kodlama.io.programmingLanguages.entities.concretes.Languages;

@Service
public class LanguageManager implements LanguagesService {

	private LanguagesRepository languagesRepository;
	private FrameworksRepository frameworksRepository;

	@Autowired
	public LanguageManager(LanguagesRepository languagesRepository, FrameworksRepository frameworksRepository) {
		this.languagesRepository = languagesRepository;
		this.frameworksRepository = frameworksRepository;
	}

	@Override
	public List<GetAllLanguageResponse> getAll() {
		List<Languages> languages = languagesRepository.findAll();
		List<GetAllLanguageResponse> languageResponsesList = new ArrayList<>();

		for (Languages language : languages) {
			GetAllLanguageResponse responseItem = new GetAllLanguageResponse();
			responseItem.setId(language.getId());
			responseItem.setName(language.getName());

			languageResponsesList.add(responseItem);
		}

		return languageResponsesList;
	}

	@Override
	public GetByIdLanguageResponse getById(int id) {
		Languages language = languagesRepository.findById(id).get();
		GetByIdLanguageResponse getByIdLanguageResponse = new GetByIdLanguageResponse();
		getByIdLanguageResponse.setId(language.getId());
		getByIdLanguageResponse.setName(language.getName());
		return getByIdLanguageResponse;
	}

	@Override
	public void update(UpdateLanguageRequest updateLanguageRequest) throws Exception {
		if (languagesRepository.existsById(updateLanguageRequest.getId())) {
			if (inputNameCheck(updateLanguageRequest.getName())) {
				Languages language = new Languages();
				language.setId(updateLanguageRequest.getId());
				language.setName(updateLanguageRequest.getName());
				languagesRepository.save(language);
			} else {
				throw new Exception("The entered langugae cannot be the same or empty.");
			}
		} else {
			throw new Exception("The entered id value does not exist.");
		}

	}

	@Override
	public void add(CreateLanguageRequest createLanguageRequest) throws Exception {
		if (inputNameCheck(createLanguageRequest.getName())) {
			Languages language = new Languages();
			language.setName(createLanguageRequest.getName());
			languagesRepository.save(language);
		} else {
			throw new Exception("The entered langugae cannot be the same or empty.");
		}

	}

	@Override
	public void delete(DeleteLanguageRequest deleteLanguageRequest) throws Exception {
		DeleteFrameworkRequest deleteFrameworkRequest = new DeleteFrameworkRequest();
		Languages language = new Languages();
		List<Frameworks> allFrameworks = frameworksRepository.findAll();
		language.setId(deleteLanguageRequest.getId());

		if (languagesRepository.existsById(language.getId())) {
			for (Frameworks frameworks : allFrameworks) {
				if (frameworks.getLanguageId().getId() == language.getId()) {
					deleteFrameworkRequest.setId(frameworks.getFrameworkId());
					frameworksRepository.deleteById(deleteFrameworkRequest.getId());
				}
			}
			languagesRepository.deleteById(language.getId());
		} else {
			throw new Exception("The entered id value does not exist.");
		}

	}

	public boolean inputNameCheck(String name) {
		List<Languages> languages = languagesRepository.findAll();

		for (Languages language : languages) {

			if (language.getName().equalsIgnoreCase(name) || Strings.isNullOrEmpty(name)) {
				return false;
			}
		}
		return true;
	}

}
