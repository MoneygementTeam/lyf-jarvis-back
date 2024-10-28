package com.moneygement.lyf.jarvis.recommendation.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recommendation")
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "group_id", nullable = false)
	private Long groupId;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", length = 2500)
	private String description;

	@Column(name = "category", length = 50)
	private String category;

	@Column(name = "latitude", nullable = false)
	private Double latitude;

	@Column(name = "longitude", nullable = false)
	private Double longitude;

	@Column(name = "rating")
	private Double rating = 0.0;

	public Recommendation(Long groupId, String name,
		String description,
		String category,
		Double latitude, Double longitude) {
		this.groupId = groupId;
		this.name = name;
		this.description = description;
		this.category = category;
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
