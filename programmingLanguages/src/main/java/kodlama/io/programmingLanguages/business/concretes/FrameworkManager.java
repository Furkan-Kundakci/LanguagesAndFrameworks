package kodlama.io.programmingLanguages.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import kodlama.io.programmingLanguages.business.abstracts.FrameworksService;
import kodlama.io.programmingLanguages.business.requests.CreateFrameworksRequest;
import kodlama.io.programmingLanguages.business.requests.DeleteFrameworkRequest;
import kodlama.io.programmingLanguages.business.requests.UpdateFrameworkRequest;
import kodlama.io.programmingLanguages.business.responses.GetAllFrameworksResponse;
import kodlama.io.programmingLanguages.business.responses.GetByIdFrameworksResponse;
import kodlama.io.programmingLanguages.dataAccess.abstracts.FrameworksRepository;
import kodlama.io.programmingLanguages.dataAccess.abstracts.LanguagesRepository;
import kodlama.io.programmingLanguages.entities.concretes.Frameworks;
import kodlama.io.programmingLanguages.entities.concretes.Languages;

@Service
public class FrameworkManager implements FrameworksService {

	private FrameworksRepository frameworksRepository;
	private LanguagesRepository languagesRepository;

	@Autowired
	public FrameworkManager(FrameworksRepository frameworksRepository, LanguagesRepository languagesRepository) {
		this.frameworksRepository = frameworksRepository;
		this.languagesRepository = languagesRepository;
	}

	@Override
	public List<GetAllFrameworksResponse> getAll() {
		List<Frameworks> frameworkList = frameworksRepository.findAll();
		List<GetAllFrameworksResponse> frameworkResponseList = new ArrayList<>();

		for (Frameworks framework : frameworkList) {
			GetAllFrameworksResponse responseItem = new GetAllFrameworksResponse();
			Languages languages = languagesRepository.findById(framework.getLanguageId().getId()).get();
			responseItem.setId(framework.getFrameworkId());
			responseItem.setName(framework.getFrameworkName());
			responseItem.setLanguageId(languages.getName());
			frameworkResponseList.add(responseItem);
		}

		return frameworkResponseList;
	}

	@Override
	public GetByIdFrameworksResponse getById(int id) {
		Frameworks frameworks = frameworksRepository.findById(id).get();
		GetByIdFrameworksResponse getByIdFrameworksResponse = new GetByIdFrameworksResponse();
		getByIdFrameworksResponse.setId(frameworks.getFrameworkId());
		getByIdFrameworksResponse.setName(frameworks.getFrameworkName());
		return getByIdFrameworksResponse;
	}

	@Override
	public void update(UpdateFrameworkRequest updateFrameworkRequest) throws Exception {
		if (frameworksRepository.existsById(updateFrameworkRequest.getId())) {
			if (languagesRepository.existsById(updateFrameworkRequest.getLanguageId())) {
				if (inputNameCheck(updateFrameworkRequest.getName())) {
					Languages language = languagesRepository.getReferenceById(updateFrameworkRequest.getLanguageId());
					Frameworks framework = new Frameworks();
					framework.setFrameworkId(updateFrameworkRequest.getId());
					framework.setFrameworkName(updateFrameworkRequest.getName());
					framework.setLanguageId(language);
					frameworksRepository.save(framework);
				} else {
					throw new Exception("Invalid name entry.");
				}
			}
		} else {
			throw new Exception("Invalid ID entry.");
		}
	}

	@Override
	public void add(CreateFrameworksRequest createFrameworksRequest) throws Exception {
		if (inputNameCheck(createFrameworksRequest.getName())) {
			Languages language = languagesRepository.getReferenceById(createFrameworksRequest.getLanguageId());
			Frameworks framework = new Frameworks();
			framework.setFrameworkName(createFrameworksRequest.getName());
			framework.setLanguageId(language);
			frameworksRepository.save(framework);
		} else {
			throw new Exception("Invalid name entry.");
		}
	}

	@Override
	public void delete(DeleteFrameworkRequest deleteFrameworkRequest) throws Exception {
		if (frameworksRepository.existsById(deleteFrameworkRequest.getId())) {
			frameworksRepository.deleteById(deleteFrameworkRequest.getId());
		} else {
			throw new Exception("Invalid ID entry.");
		}

	}

	public boolean inputNameCheck(String name) {
		List<Frameworks> listFramework = frameworksRepository.findAll();
		Optional<Frameworks> framework = listFramework.stream()
				.filter(frameworks -> frameworks.getFrameworkName().equalsIgnoreCase(name)).findFirst();
		
		if (framework.isPresent() || Strings.isNullOrEmpty(name)) {
			return false;
		}
		return true;
	}

}
