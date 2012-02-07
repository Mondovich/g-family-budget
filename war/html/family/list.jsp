<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="listOfPerson">
	<div class="item clickable ui-corner-all" id='<s:property value="%{key.id}" />'>
		<s:property value="firstName" />&nbsp;<s:property value="lastName" /><br />
	</div>
</s:iterator>