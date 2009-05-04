/*******************************************************************************
 *Copyright (c) Members of the EGEE Collaboration. 2006. 
 *See http://www.eu-egee.org/partners/ for details on the copyright
 *holders.  
 *
 *Licensed under the Apache License, Version 2.0 (the "License"); 
 *you may not use this file except in compliance with the License. 
 *You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0 
 *
 *Unless required by applicable law or agreed to in writing, software 
 *distributed under the License is distributed on an "AS IS" BASIS, 
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *See the License for the specific language governing permissions and 
 *limitations under the License.
 *
 * Authors:
 *     Andrea Ceccanti - andrea.ceccanti@cnaf.infn.it
 *******************************************************************************/
package org.glite.security.voms.admin.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.glite.security.voms.admin.common.Constants;
import org.glite.security.voms.admin.common.VOMSConfiguration;
import org.glite.security.voms.admin.dao.SearchResults;
import org.glite.security.voms.admin.model.VOMSGroup;
import org.glite.security.voms.admin.model.VOMSRole;
import org.glite.security.voms.admin.model.VOMSUser;



public class BaseAction extends Action {

    protected Log log = LogFactory.getLog( BaseAction.class );

    protected ActionForward findSuccess( ActionMapping mapping ) {

        return mapping.findForward( Constants.SUCCESS );

    }

    protected ActionForward findFailure( ActionMapping mapping ) {

        return mapping.findForward( Constants.FAILURE );

    }

    protected ActionForward findConfirm( ActionMapping mapping ) {

        return mapping.findForward( Constants.CONFIRM );
    }

    protected void storeUser( HttpServletRequest request, VOMSUser u ) {

        ActionUtils.storeUser( request, u );

    }

    protected void storeGroup( HttpServletRequest request, VOMSGroup g ) {

        ActionUtils.storeGroup( request, g );

    }

    protected void storeRole( HttpServletRequest request, VOMSRole r ) {

        ActionUtils.storeRole( request, r );

    }

    protected void storeResults( HttpServletRequest request, SearchResults r ) {

        ActionUtils.storeResults( request, r );
    }
    
    protected VOMSUser getUserFromRequest(HttpServletRequest request){
        return ActionUtils.getUserFromRequest(request);
    }
    
    protected VOMSGroup getGroupFromRequest(HttpServletRequest request){
        return ActionUtils.getGroupFromRequest(request);
    }
    
    protected VOMSRole getRoleFromRequest(HttpServletRequest request){
        
        return ActionUtils.getRoleFromRequest(request);
    }
    
    protected SearchResults getResultsFromRequest(HttpServletRequest request){
        return ActionUtils.getResultsFromRequest(request);
    }
    
    protected String getBaseContext(HttpServletRequest request){
        
        String result = request.getScheme()+"://"+
                        request.getServerName()+":"+
                        request.getServerPort()+"/voms/"+
                        VOMSConfiguration.instance().getVOName();
        
        return result;
    }
    
    protected void message(HttpServletRequest req, String template, String param){
        
        ActionMessages msgs = new ActionMessages();
        
        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                template, param));
        
        saveMessages(req, msgs);
    }
}
