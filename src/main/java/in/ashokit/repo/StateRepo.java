package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity,Integer>{
	
	
	@Query(value="select * from STATE_MASTER where country_id=:cid",nativeQuery=true)
	public List<StateEntity> getStates(Integer cid);

}
