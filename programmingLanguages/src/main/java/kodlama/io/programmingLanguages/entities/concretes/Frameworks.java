package kodlama.io.programmingLanguages.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "frameworks")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Frameworks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "frameworkId")
	private int frameworkId;
	@Column(name = "frameworkName")
	private String frameworkName;

	@ManyToOne
	@JoinColumn(name = "languageId")
	private Languages languageId;
}
