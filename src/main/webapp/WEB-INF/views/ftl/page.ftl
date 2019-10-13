<#-- 分页（Pager对象、链接URL、参数Map、最大页码显示数） -->
<#macro pager pager baseUrl parameterMap = {} maxShowPageCount = 4 showTxtNav = 1>

	<#local pageNumber = pager.pageNum />
	<#local pageSize = pager.pageSize />
	<#local pageCount = pager.pages /> <#--总页数-->
	<#local parameter = "" />

	<#local parameter = parameter + "&limit=" + pageSize />

	<#list parameterMap?keys as key>
		<#if parameterMap[key] != null && parameterMap[key] != "">
			<#local parameter = parameter + "&" + key + "=" + parameterMap[key] />
		</#if>
	</#list>

	<#if baseUrl?contains("?")>
		<#local baseUrl = baseUrl + "&" />
	<#else>
		<#local baseUrl = baseUrl + "?" />
	</#if>

	<#local firstPageUrl = baseUrl + "page=1" + parameter />
	<#local lastPageUrl = baseUrl + "page=" + pageCount + parameter />
	<#local prePageUrl = baseUrl + "page=" + (pageNumber - 1) + parameter />
	<#local nextPageUrl = baseUrl + "page=" + (pageNumber + 1) + parameter />


	<#if maxShowPageCount <= 0>
		<#local maxShowPageCount = 4>
	</#if>


	<#local segment = ((pageNumber - 1) / maxShowPageCount)?int + 1 />

	<#local startPageNumber = ( (segment - 1) * maxShowPageCount +1 ) - 1/>
	<#local endPageNumber = (segment * maxShowPageCount) + 1/>


	<#if (startPageNumber < 1)>
		<#local startPageNumber = 1 />
	</#if>

	<#if (endPageNumber > pageCount)>
		<#local endPageNumber = pageCount />
	</#if>


	<#if (pageCount > 1)>
		<#--
		<li class="pageInfo">
			共 ${segment} 页
		</li>
		-->

		<#-- 上一页 -->
		<#if (pageNumber > 1)>
			<a class="zhise" href="${prePageUrl}"><i></i> <#if showTxtNav=1>previous page</#if></a>
		<#else>
			<a class="zhise"><i></i><#if showTxtNav=1>previous page</#if></a>
		</#if>


		<#if (startPageNumber > 1)>
			<a href="${baseUrl + "page=" + 1 + parameter}">1</a>
			<#-- <a href="${baseUrl + "pageNumber=" + (pageNumber - 2) + parameter}">...</a> -->
			<a>...</a>
		</#if>


		<#list startPageNumber .. endPageNumber as index>

			<#if pageNumber != index>
				<a href="${baseUrl + "page=" + index + parameter}">${index}</a>
			<#else>
				<a class="current">${index}</a>
			</#if>

		</#list>

		<#if (endPageNumber < pageCount)>
			<#-- <a href="${baseUrl + "pageNumber=" + (pageNumber + 2) + parameter}">...</a> -->
			<a>...</a>
			<a href="${baseUrl + "page=" + pageCount + parameter}">${pageCount}</a>
		</#if>


		<#-- 下一页 -->
		<#if (pageNumber < pageCount)>
			<a class="zhise" href="${nextPageUrl}"><#if showTxtNav=1>next page</#if><i></i></a>
		<#else>
			<a class="zhise"><#if showTxtNav=1>next page</#if><i></i></a>
		</#if>


	</#if>

</#macro>