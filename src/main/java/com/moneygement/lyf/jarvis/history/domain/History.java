package com.moneygement.lyf.jarvis.history.domain;

import com.moneygement.lyf.jarvis.common.audit.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "history")
public class History extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "request", length = 2500, nullable = false)
	private String request;

	@Column(name = "response", length = 2500, nullable = false)
	private String response;

	public History(String userId, Long groupId, String request, String response) {
		this.userId = userId;
		this.groupId = groupId;
		this.request = request;
		this.response = response;
	}

}
