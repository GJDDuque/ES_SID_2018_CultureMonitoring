package cultura.measure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MeasureServiceImpl implements MeasureService{
	private JdbcTemplate jdbcTemplate;
	final String INSERT_QUERY = "insert into measures (measure_id, date_time, measured_value, user, measured_variable_id)"
			+ " values (?, ?, ?, ?, ?)";
    final String UPDATE_QUERY = "update measures set measured_value = ? where measure_id = ?";
    final String DELETE_QUERY = "delete from measures where measure_id = ?";
	
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	this.jdbcTemplate = jdbcTemplate;
    }
    
    public int save(Measure measure) {
        return jdbcTemplate.update(INSERT_QUERY, measure.getMeasureId(), measure.getDatetime(), measure.getMeasuredValue(),
        		measure.getUser(), measure.getMeasuredVariableId());   
    }

    public void update(Measure measure) {
        int status = jdbcTemplate.update(UPDATE_QUERY, measure.getMeasuredValue(), 
        		measure.getMeasureId()); 
        if(status != 0){
            System.out.println("Measure data updated for ID " + measure.getMeasureId());
        }else{
            System.out.println("No Measure found with ID " + measure.getMeasureId());
        }       
    }

    public void deleteEmpById(int empId) {
        int status = jdbcTemplate.update(DELETE_QUERY, empId);
        if(status != 0){
            System.out.println("Measure data deleted for ID " + empId);
        }else{
            System.out.println("No Measure found with ID " + empId);
        }
    }
    
    
//	public void setTemplate(JdbcTemplate template) {
//		this.template = template;
//	}
	
	
//	public void saveMeasure(Measure measure) {
//		String query = "insert into measures values(" + measure.getMeasureId() + ", " + 
//				measure.getDatetime() + ", " + measure.getMeasuredValue() + ", " + measure.getUser()
//				+ ", "+ measure.getMeasuredVariableId();
//		template.update(query);
//	}
	
}
