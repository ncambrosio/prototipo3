/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package yes.share.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Clase para cargar el application.properties
 * 
 * @author Noé Comesaña
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
