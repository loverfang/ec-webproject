<#macro navigater navList>
   <#if navList??>

        <#list navList?reverse as nav> 
	    <span><a href="/system/toChannelPage.do?channelId=${nav.channelId}" class="on">${nav.title}</a></span>
	      <#if nav_has_next>
		   <span>&gt;</span>
	      </#if>
	</#list>

   </#if>
</#macro>





<#macro channelselect channelList>
    <#if channelList??>
        <#list channelList as channel>
    
		<#if (channel.parentId == 1) || (channel.parentId == -1) >
			<option value="${channel.channelId!''}">${channel.title!''}</option>
		<#else>
			<#assign level=channel.level - 1/>
			<#assign path=""/>

			<#list 1..level as t>
			    <#assign tag=""/>
			    
			    <#if t == level>
			      
			      <#if channel.isLevelLast == 1>
				  <#assign tag="└─"/>
			      <#else>
				  <#assign tag="├─"/>
			      </#if>
			      
			    <#else>
				<#assign tag="│"/> 
			    </#if>
			    
			    <#assign path = path + "&nbsp;&nbsp;&nbsp;&nbsp;" + tag />
			</#list>
					
			<#assign path= path + channel.title />
			<option value="${channel.channelId!''}">${path}</option>
		</#if>
    
        </#list>
    <#else>
        <option value="1">≡ 作为一级栏目 ≡</option>
    </#if>
</#macro>


