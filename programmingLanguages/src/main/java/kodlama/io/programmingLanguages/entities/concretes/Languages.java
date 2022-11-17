package kodlama.io.programmingLanguages.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "langugaes")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Languages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "languageId")
	private int id;
	@Column(name = "languageName")
	private String name;
	@Column(name = "status")
	private boolean status;

	@OneToMany(targetEntity = Frameworks.class, mappedBy = "languageId", fetch = FetchType.LAZY)
	private List<Frameworks> frameworkList;
}
