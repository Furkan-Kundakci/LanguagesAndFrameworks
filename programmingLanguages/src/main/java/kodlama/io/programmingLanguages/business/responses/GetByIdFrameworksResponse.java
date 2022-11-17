package kodlama.io.programmingLanguages.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdFrameworksResponse {
	private int id;
	private String name;
	private int languageId;
}
