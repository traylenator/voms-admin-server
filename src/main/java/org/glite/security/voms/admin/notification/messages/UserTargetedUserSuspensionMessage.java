/**
 * Copyright (c) Members of the EGEE Collaboration. 2006-2009.
 * See http://www.eu-egee.org/partners/ for details on the copyright holders.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors:
 * 	Andrea Ceccanti (INFN)
 */
package org.glite.security.voms.admin.notification.messages;

import org.glite.security.voms.admin.configuration.VOMSConfiguration;
import org.glite.security.voms.admin.persistence.model.VOMSUser;

public class UserTargetedUserSuspensionMessage extends
		AbstractVelocityNotification {

	VOMSUser user;
	String suspensionReason;
	
	public UserTargetedUserSuspensionMessage(VOMSUser u, String suspensionReason) {
		
		this.user = u;
		this.suspensionReason = suspensionReason;

	}
	
	@Override
	protected void buildMessage() {

		VOMSConfiguration conf = VOMSConfiguration.instance();
		String voName = conf.getVOName();

		setSubject("Membership suspension notification");
		
		context.put("voName", voName);
		context.put("recipient", getRecipientList().get(0));
		context.put("suspensionReason", suspensionReason);
		context.put("user", user);
		
		super.buildMessage();
	}

}
