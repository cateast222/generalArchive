package com.ebs.platform.core;

import com.ebs.platform.core.conf.AutoSaveGeneralFieldListener;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AutoSaveGeneralFieldListener.class)
@FilterDef(name = "notDeleted")
@Filters({
		@Filter(name = "notDeleted", condition = "is_deleted = 0")
})
public class BaseAutoIncrementEntity implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "is_deleted", nullable = false)
	private Boolean deleted = false;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "operator", length = 100)
	private String operator;

	@Column(name = "update_man", length = 100)
	private String updateMan;

}
