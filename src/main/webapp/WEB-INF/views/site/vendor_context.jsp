<!DOCTYPE HTML>
<html>
<head>
	<title>vendor directory</title>
    <link href="${base}/res/site/style/default.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/site/style/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<#include "/site/components/title.html"/>
<#include "/site/components/navbar.html"/>

<div class="maincontent">
  <div class="seach-area">
	<div class="fl" >
		<div class="vendors-all" style="display:block;opacity:0;cursor: pointer;">
			<ul><c:forEach items="${categoryList}" var="item" varStatus="current">
				<li <c:if test="${cid eq item.cid}">class="on"</c:if> ><a href="/vendors/c${item.cid}/">${item.cname}</a></li></c:forEach> 
			</ul>
		</div>
		<div class="xiala-tit"><a href="/vendors/"><img src="/images/flei.jpg" width="142" height="50"></a></div>
	</div>
		
	<form action="/vendors/" method="post">
		<input type="hidden" name="cid" value="${cid}" />
		
		<div class="inp-con">
			<input type="text" name="name" placeholder="Enter vendor name" class="inp" <c:if test="${!empty cname}">value="${cname}"</c:if>/>
		</div>
		
		<div class="btn-con">
			<input type="submit" value=" "  class="btn" />
		</div>
	</form>
  </div>
  
  <div class="conmpanydiv">
    <dl>
      <dt class="imp f24px huise2">COMPANY NAME </dt>
      <dd>
        <input type="text" class="inp" value="${product.name}">
      </dd>
    </dl>
    <dl>
      <dt class="imp f24px huise2">CATEGORY  </dt>
      <dd>
        <input type="text" class="inp" value="${currentCategory.cname}">
      </dd>
    </dl>
  </div>
  
  <!--  one-->
  <div class="wrap ">
    <div class="mainleft fl">
      <div class="down ">
        <p class="oneleft"> <img src="/upload/products/PIC_${product.cid}_${product.pid}.jpg" width="290" height="170"></p>
        <p class="f16px lh24 p20">
	        <span class="underline"><a href="${product.doman}">${product.doman}</a></span>
	        <br/>
	        <span class="imp f24px lh30">${product.name}</span><br/>
	        ${product.info}
	    </p>
      </div>
    </div>
    
    <div class="mainright fr">
      <div class="tit">Company Introduction</div>
      <div class="con">
         ${product.introduction}
      </div>
    </div>
  </div>
  <!-- two-->
  <div class="wrap">
    <div class="mainleft fl">
      <c:if test="${!empty pdfList}">
	  <c:forEach items="${pdfList}" var="item">
      <div class="huisebj">
        <div class="pdfimg"><img src="/upload/news/pdf/IMG_${item.pid}.jpg" width="270" height="360"></div>
      </div> </c:forEach>
	  </c:if>
      
      <div class="down p20">
        <c:if test="${!empty pdfList}">
		<c:forEach items="${pdfList}" var="item">
        <p class="btn">
        <!-- 
        <c:if test="${empty member}">
			<a href="javascript:void(0);" onclick="openLoginLayer();">Download brochure</a>
		</c:if>
        <c:if test="${!empty member}">
			<a href="/front/downpdfUpload.action?id=${item.pid}">Download brochure</a>
		</c:if>
		-->
		<a href="/front/downpdfUpload.action?id=${item.pid}">Download brochure</a>
        </p>
        </c:forEach>
        </c:if>
        <p class="btn-2"><a href="/marketingwithus/">About VCI Marketing with Us ®</a></p>
        <p class="red f16px pb20 pt10"><a href="/stories/">Learn more about successful stories</a></p>
      </div> 
      
    </div>
    <div class="mainright fr">
      <div class="tit">Solutions</div>
      <div class="con">
       ${product.solutions}
      </div>
    </div>
  </div>
  <!--  three-->
  <div class="wrap">
    <div class="mainleft fl">
      <div class="blackline"></div>
      <div class="down p20">
        <p class="imp f24px lh30 pb20">The VCI Vendors Directory is the database dedicated to help supply chain, procurement, and logistics professionals to find the products &amp; services they need.</p>
        <p class="btn"><a href="Mailto:demand@vcintegration.com?subject=I would like to add listing at VCI Supply Chain and Logistics Vendors Directory">Add Listing </a></p>
        <p class="f16px lh24 pt10">Contact us to add listing<br>
          <a href="Mailto:demand@vcintegration.com" class="underline">demand@vcintegration.com </a></p>
      </div>
    </div>
    <div class="mainright fr">
      <div class="tit">Highlights<br>
      </div>
      <div class="con">
        ${product.highlights}
      </div>
    </div>
  </div>
</div>
<jsp:include page="/footer.jsp" />
<script type="text/javascript" src="/js/common_login.js"></script> 
<script type="text/javascript">
$(window).load(function(){
	//显示
    $(".fl").on("mouseenter",".xiala-tit",function(e){
        $(".vendors-all").show();
        $(".vendors-all").animate({opacity:'1'},500);
        e.stopPropagation();
    });
    
    $(".fl").on("mouseleave",function(e){
        $(".vendors-all").animate({opacity:'0'},500,function(){
            $(this).hide();
        });
        e.stopPropagation();
    });
});
</script>
</body>
</html>