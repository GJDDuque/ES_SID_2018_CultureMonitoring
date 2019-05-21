package cultura.variable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("variableService")
public class VariableServiceImpl implements VariableService {
	
	@Autowired
	private VariableRepository variableRepository;
	
	@Override
	public void saveVariable(VariableForm variableForm) {
		variableRepository.save(new Variable(variableForm.getName()));
		
	}

}
