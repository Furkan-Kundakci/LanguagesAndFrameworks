package kodlama.io.programmingLanguages.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.programmingLanguages.business.abstracts.FrameworksService;
import kodlama.io.programmingLanguages.business.requests.CreateFrameworksRequest;
import kodlama.io.programmingLanguages.business.requests.DeleteFrameworkRequest;
import kodlama.io.programmingLanguages.business.requests.UpdateFrameworkRequest;
import kodlama.io.programmingLanguages.business.responses.GetAllFrameworksResponse;
import kodlama.io.programmingLanguages.business.responses.GetByIdFrameworksResponse;

@RestController
@RequestMapping("/api/frameworks")
public class FrameworksController {

	private FrameworksService frameworksService;

	@Autowired
	public FrameworksController(FrameworksService frameworksService) {
		this.frameworksService = frameworksService;
	}

	@GetMapping("/getAll")
	public List<GetAllFrameworksResponse> getAll() {
		return frameworksService.getAll();
	}

	@GetMapping("/getById/{id}")
	public GetByIdFrameworksResponse getById(@PathVariable(name = "id") int id) {
		return frameworksService.getById(id);
	}

	@PostMapping("/add")
	public void add(@RequestBody CreateFrameworksRequest createFrameworksRequest) throws Exception {
		frameworksService.add(createFrameworksRequest);
	}

	@PutMapping
	public void update(@RequestBody UpdateFrameworkRequest updateFrameworkRequest) throws Exception {
		frameworksService.update(updateFrameworkRequest);
	}

	@DeleteMapping
	public void delete(@RequestBody DeleteFrameworkRequest deleteFrameworkRequest) throws Exception {
		frameworksService.delete(deleteFrameworkRequest);
	}

}
