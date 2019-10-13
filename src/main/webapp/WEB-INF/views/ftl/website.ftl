<#--页面导航标签-->
<#macro navigater navList navId>
   <#if navList??>
    <ul class="nav">
       <li <#if navId??><#if navId==0>class="on"</#if></#if>><a href="/index.html">网站首页</a></li>
       <#list navList as nav>
	    <#if nav??>
               <#if nav.channelId !=43>
                <li <#if navId == nav.channelId>class="on"</#if><a href="#">${nav.title!''}</a><#if nav.childChannel??>
                    <ul class="nav1"><#list nav.childChannel as childNav><#if childNav??>
                         <li><a href="/news/${childNav.channelId}.html">${childNav.title!''}</a></li></#if></#list>
                    </ul></#if>
                </li>
               <#else>
                <li <#if navId == nav.channelId>class="on"</#if><a href="/products.html">${nav.title!''}</a><#if nav.childChannel??>
                    <ul class="nav1"><#list nav.childChannel as childNav><#if childNav??>
                         <li><a href="/products/${childNav.channelId}.html">${childNav.title!''}</a></li></#if></#list>
                    </ul></#if>
                </li>
               </#if>

            </#if>
       </#list>
    </ul>
   </#if>
</#macro>



<#--footer 标签-->
<#macro footer navList>
   <#if navList??>
    <#list navList as nav>
    
      <#if nav??>
          <#if (nav.channelId!=38 && nav.channelId!=40)>

<#if nav.channelId !=43>
          <dl>
              <dt>${nav.title!''}<i>${nav.englishName!''}</i></dt>

              <#if nav.childChannel??><#list nav.childChannel as childNav><#if childNav??>
              <dd><a href="/news/${childNav.channelId}.html">${childNav.title!''}</a></dd></#if></#list>
              </#if>
           </dl>
<#else>
          <dl>
              <dt>${nav.title!''}<i>${nav.englishName!''}</i></dt>

              <#if nav.childChannel??><#list nav.childChannel as childNav><#if childNav??>
              <dd><a href="/products/${childNav.channelId}.html">${childNav.title!''}</a></dd></#if></#list>
              </#if>
           </dl>
</#if>

          </#if>
      </#if>

    </#list>
    <dl>
      <dt>微信公众号<i>WeChat</i></dt>
      <dd><a href="#"> <img src="${base}/res/website/images/weixin.jpg" width="121" height="121"></a></dd>
    </dl>
   </#if>
</#macro>

<#--首页新闻选项卡 标签-->
<#macro indexNews channelList>
<#local newsList_0 = "" />
<#local newsList_1 = "" />
<#local newsList_2 = "" />
    <div class="i-news">
      <div class="i-newstab fl">
        <div class="tab-hide ">
            <#if newsChannel??><#list newsChannel as news>
            <a href="/news/${news.channelId!''}.html" <#if news_index == 0>class="active"</#if> >${news.title!''}<i>WO  SHENG NEWS</i></a>
            <#if news_index == 0>
                 <#local newsList_0=news.contentList/>
            </#if>
            <#if news_index == 1>
                 <#local newsList_1=news.contentList/>
            </#if>
            <#if news_index == 2>
                 <#local newsList_2=news.contentList/>
            </#if>
            </#list></#if>
        </div>
      </div>

      <div class="tab-bd fl padding-l-35"> 
        <!--板式屏风-->
        <div class="thisclass">
          <div class="i-newslist">
            <ul><#if newsList_0??><#list newsList_0 as news0>
              <li><a href="/news/${news0.channelId!''}/${news0.contentId!''}.html"><span class="tit ">${news0.title!''}</span> <span class="time">${news0.releaseDate?string("yyyy-MM-dd ")}</span></a></#list> </li></#if>
            </ul>
          </div>
        </div>
        <!--实木油漆-->
        <div class="disn">
          <div class="i-newslist">
            <ul><#if newsList_1??><#list newsList_1 as news1>
              <li><a href="/news/${news1.channelId!''}/${news1.contentId!''}.html"><span class="tit ">${news1.title!''}</span> <span class="time">2017-02-20</span></a></#list> </li></#if>
            </ul>
          </div>
        </div>
        <!-- 软体沙发-->
        <div class="disn">
          <div class="i-newslist">
            <ul><#if newsList_2??><#list newsList_2 as news2>
              <li><a href="/news/${news2.channelId!''}/${news2.contentId!''}.html"><span class="tit ">${news2.title!''}</span> <span class="time">2017-02-20</span></a></#list> </li></#if>
            </ul>
          </div>
        </div>
      </div>
    </div>
</#macro>