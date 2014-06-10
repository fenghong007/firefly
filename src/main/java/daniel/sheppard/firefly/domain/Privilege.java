package daniel.sheppard.firefly.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Privilege implements Serializable{

	private static final long serialVersionUID = 403114940615332930L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(length = 32)
	private String id;

	@Column
	private String subjectType;

	@Column
	private String subjectId;

	@Column
	private String realmType;

	@Column
	private String realmId;

	@Column
	private String flag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
