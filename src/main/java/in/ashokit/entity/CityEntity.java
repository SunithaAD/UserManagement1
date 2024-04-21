package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CITY_MASTER")
public class CityEntity {
	@Id
	private Integer cityId;
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name="state_id")
	private StateEntity state;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = state;
	}
	
	

}
