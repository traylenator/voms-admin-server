package org.glite.security.voms.admin.test;

import java.net.URL;
import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.glite.security.voms.admin.common.VOMSConfiguration;
import org.glite.security.voms.admin.common.tasks.DatabaseSetupTask;
import org.glite.security.voms.admin.common.tasks.UpdateCATask;
import org.glite.security.voms.admin.dao.VOMSCADAO;
import org.glite.security.voms.admin.dao.VOMSUserDAO;
import org.glite.security.voms.admin.model.VOMSUser;
import org.hibernate.transaction.ResinTransactionManagerLookup;


public class TestUtils {
    
    static final Log log = LogFactory.getLog( TestUtils.class );
    static final String vo = "test_vo_mysql";
    
    static final Timer t = new Timer();
    
    static final String myDn = "/C=IT/O=INFN/OU=Personal Certificate/L=CNAF/CN=Andrea Ceccanti";
    static final String myCA = "/C=IT/O=INFN/CN=INFN CA";
    static final String myEmail = "andrea.ceccanti@cnaf.infn.it";
    
    public static void configureLogging(){
        
    	URL loggingConf = Object.class.getResource( "/test/log4j.properties" );
        PropertyConfigurator.configure( loggingConf );
    }
    
    public static void setupVOMSConfiguration(){
        
        log.debug("Setting up voms configuration...");
        System.setProperty( VOMSConfiguration.VO_NAME, vo );
        System.setProperty( "GLITE_LOCATION_VAR", "./resources/debug/conf" );
        VOMSConfiguration.instance( false, null );  
        log.debug("Setting up voms configuration done!");
    }
    
    public static void setupVOMSDB(){
        
        UpdateCATask caTask = UpdateCATask.instance( t );
        caTask.run();

        DatabaseSetupTask task = DatabaseSetupTask.instance();
        task.run();
        
    }
    
    public static VOMSUser createUser(){
    	
    	VOMSUserDAO dao =  VOMSUserDAO.instance();
    	
    	VOMSUser u = dao.findByDNandCA(myDn, myCA); 
    	if ( u == null){
    		
    		u = new VOMSUser();
    		u.setDn(myDn);
    		u.setEmailAddress(myEmail);
    		dao.create(u,myCA);	  		
    	}
    	
    	return u;
    }
    
    public static VOMSUser getUser(){
    	
    	return VOMSUserDAO.instance().findByDNandCA(myDn, myCA);
    }

}
