package kodlama.io.programmingLanguages.business.abstracts;

import java.util.List;

import kodlama.io.programmingLanguages.business.requests.CreateFrameworksRequest;
import kodlama.io.programmingLanguages.business.requests.DeleteFrameworkRequest;
import kodlama.io.programmingLanguages.business.requests.UpdateFrameworkRequest;
import kodlama.io.programmingLanguages.business.responses.GetAllFrameworksResponse;
import kodlama.io.programmingLanguages.business.responses.GetByIdFrameworksResponse;

public interface FrameworksService {

	List<GetAllFrameworksResponse> getAll();

	GetByIdFrameworksResponse getById(int id);

	void update(UpdateFrameworkRequest updateFrameworkRequest) throws Exception;

	void add(CreateFrameworksRequest createFrameworksRequest) throws Exception;

	void delete(DeleteFrameworkRequest deleteFrameworkRequest) throws Exception;

}
