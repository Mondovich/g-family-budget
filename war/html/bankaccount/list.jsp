<%@ taglib prefix="s" uri="/struts-tags"%>


<s:iterator value="listOfBankAccount">
	<div class="item clickable ui-corner-all" id='<s:property value="%{key.id}" />'>
		<s:property value="name" />
		<div class="float_r money">
			&euro;&nbsp;<s:property value="initialValue" />
		</div>
	</div>
</s:iterator>