package kodlama.io.programmingLanguages.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.programmingLanguages.business.abstracts.LanguagesService;
import kodlama.io.programmingLanguages.business.requests.CreateLanguageRequest;
import kodlama.io.programmingLanguages.business.requests.DeleteLanguageRequest;
import kodlama.io.programmingLanguages.business.requests.UpdateLanguageRequest;
import kodlama.io.programmingLanguages.business.responses.GetAllLanguageResponse;
import kodlama.io.programmingLanguages.business.responses.GetByIdLanguageResponse;

@RestController
@RequestMapping("/api/languages")
public class LangugageController {

	private LanguagesService languagesService;

	public LangugageController(LanguagesService languagesService) {
		this.languagesService = languagesService;
	}

	@GetMapping("/getAll")
	public List<GetAllLanguageResponse> getAll() {
		return languagesService.getAll();
	}

	@GetMapping("/getById/{id}")
	public GetByIdLanguageResponse getById(@PathVariable(name = "id") int id) {
		return languagesService.getById(id);
	}

	@PostMapping("/add")
	public void add(@RequestBody CreateLanguageRequest createLanguageRequest) throws Exception {
		languagesService.add(createLanguageRequest);
	}

	@PutMapping
	public void update(@RequestBody UpdateLanguageRequest updateLanguageRequest) throws Exception {
		languagesService.update(updateLanguageRequest);
	}

	@DeleteMapping
	public void delete(@RequestBody DeleteLanguageRequest deleteLanguageRequest) throws Exception {
		languagesService.delete(deleteLanguageRequest);
	}

}
