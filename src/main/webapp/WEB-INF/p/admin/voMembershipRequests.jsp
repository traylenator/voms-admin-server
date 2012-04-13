<%--

    Copyright (c) Members of the EGEE Collaboration. 2006-2009.
    See http://www.eu-egee.org/partners/ for details on the copyright holders.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

    Authors:
    	Andrea Ceccanti (INFN)

--%>
<%@include file="/WEB-INF/p/shared/taglibs.jsp"%>

<s:if
	test="not pendingRequests.{? (#this instanceof org.glite.security.voms.admin.persistence.model.request.NewVOMembershipRequest) and 
	#this.status == @org.glite.security.voms.admin.persistence.model.request.Request$STATUS@CONFIRMED }.empty">
	<h1>VO membership requests:</h1>
	<table>
		<tr>
			<th>Requester</th>
			<th>Personal Info</th>
			<th>Requested groups</th>
			<th />
		</tr>

		<s:iterator
			value="pendingRequests.{? #this instanceof org.glite.security.voms.admin.persistence.model.request.NewVOMembershipRequest and 
			#this.status == @org.glite.security.voms.admin.persistence.model.request.Request$STATUS@CONFIRMED }">
			<tr>
				<td><tiles2:insertTemplate template="userInfo.jsp" flush="true" />
				</td>

				
				<td class="personalInfo">
				<dl>
					<dt>Address:</dt>
					<dd>${requesterInfo.address}</dd>
					<dt>Phone number:</dt>
					<dd>${requesterInfo.phoneNumber}</dd>
					<dt>Email:</dt>
					<dd>${requesterInfo.emailAddress}</dd>
				</dl>
				</td>
				<s:form 
					id="req-%{id}" 
					action="membership-decision"
					onsubmit="ajaxSubmit($(this),'pending-req-content'); return false;"
					>
				
				<s:set var="requestedGroup" value="requesterInfo.getInfo('requestedGroup')"/>

				<s:if test="#requestedGroup != null">
				<td>
					${requestedGroup}
					<s:checkbox name="approvedGroups" fieldValue="%{#requestedGroup}" value="%{true}" theme="simple"/>
				</td>
				</s:if>
				
				<td style="vertical-align: bottom; text-align: right;">
					
					<s:token/>
					<s:hidden name="requestId" value="%{id}" />
					<s:hidden name="decision" value="reject"/>
					
					<s:submit 
						name="submit" 
						value="approve"
						onclick="this.form.decision.value = this.value"
						/>
					
					<s:submit 
						name="submit" 
						value="reject"
						onclick="this.form.decision.value = this.value"
						/>
				</td>
				</s:form>
			</tr>
		</s:iterator>
	</table>
</s:if>