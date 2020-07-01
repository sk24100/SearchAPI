package shared;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	
	private Map<Object,Object> scenarioContext;
	
	public ScenarioContext() {
		scenarioContext=new HashMap<Object, Object>();
	}
	
	public void setContext(Object key,Object value) {
		this.scenarioContext.put(key.toString(), value);
		
	}
	
	public Object getContext(Object key) {
		return this.scenarioContext.get(key);
	}
	
	public void resetScenarioContext() {
		this.scenarioContext.clear();
	}
	
}
