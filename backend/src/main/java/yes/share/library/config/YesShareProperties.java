package yes.share.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Clase para cargar el application.properties
 * 
 * @author noeca
 *
 */
@ConfigurationProperties(prefix = "yesshare")
public class YesShareProperties {

	/**
     * Relational database supported by SpringBoot Petclinic: hsqldb, mysql or postgresql
     */
    private String database;
    
    /**
     * Working mode: [pro]duction, [pre]production, [dev]elopment
     */
    private String workingMode;

	public String getWorkingMode() {
		return workingMode;
	}

	public void setWorkingMode(String workingMode) {
		this.workingMode = workingMode;
	}

	public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
