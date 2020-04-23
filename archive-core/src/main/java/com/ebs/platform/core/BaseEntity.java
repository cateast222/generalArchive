package com.ebs.platform.core;

import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

import javax.persistence.*;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FilterDef(name = "notDeleted")
@Filters({
		@Filter(name = "notDeleted", condition = "is_deleted = 0")
})
public class BaseEntity implements Serializable{

	@Id
	@GenericGenerator(name = "user-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "user-uuid")
	@Column(name = "id", nullable = false, length = 36)
	private String id;

	@Column(name = "is_deleted", nullable = false)
	private Boolean deleted = false;

}
