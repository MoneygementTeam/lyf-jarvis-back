package com.moneygement.lyf.jarvis.recommendation.domain;

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

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "group_recommendation")
public class GroupRecommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "session_name", length = 100)
	private String sessionName;

	@Column(name = "introduction", length = 500)
	private String introduction;

	@Column(name = "owner_user_id", nullable = false, length = 40)
	private String ownerUserId;

	public GroupRecommendation(String introduction, String ownerUserId) {
		this.introduction = introduction;
		this.ownerUserId = ownerUserId;
	}
}

