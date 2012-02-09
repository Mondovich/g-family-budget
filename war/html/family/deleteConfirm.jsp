<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form id="ajaxForm" action="delete" method="post">
	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
		<s:text name='dialog.confim.delete' >
			<s:param>
				<s:property value="person.firstName"/> <s:property value="person.lastName"/>
			</s:param>
			<s:param value="person.lastName"></s:param>
		</s:text>
	</p>
	<s:hidden name="id" value="%{person.key.id}"></s:hidden>
</s:form>