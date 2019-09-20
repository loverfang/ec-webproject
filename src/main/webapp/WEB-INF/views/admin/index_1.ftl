<!doctype html>
<html>
<head>
    <title>管理后台</title>
    <link href="${base}/res/admin/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${base}/res/admin/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="admin-body">
    <!--顶部 S-->
    <div class="ui-header">
        <div class="logo"><img src="${base}/res/admin/images/logo-s.png" width="105" height="25"></div>
        <div class="message-icon "></div>
        <div class="message-nub">8</div>
        <div class="user">嗨，<#if userName??>${userName!''}</#if><img src="${base}/res/admin/images/user.png" width="26" height="26"></div>
    </div>
    <!--顶部 E-->
    <!-- 左边 S-->
    <div class="ui-side">

    </div>
    <!-- 左边 E-->

    <!--  右边主体 S-->
    <div class="ui-body ">
        <!--<div class="red-tips">红色，表示错误</div>-->
        <div class="ui-main">
            <div class="breadcrumb">仪表盘</div>
            <div class="dashboard-left">
                <div class="fs14 blue fb mb10">网站数据统计</div>
                <div class="tabs"> <a href="#" class="on">今日</a> <a href="#">本周</a> <a href="#">本月</a> <a href="#">总计</a> </div>
                <div class="website-box ">
                    <table width="100%" class="table-td" >
                        <tr>
                            <td width="33.3%"><i class="tall-icon1"></i><cite><span class="color1">599</span><span>网站访问量</span></cite></td>
                            <td width="33.3%"><i class="tall-icon2"></i><cite><span class="color2">5</span><span>发布文章数</span></cite></td>
                            <td width="33.3%"><i class="tall-icon5"></i><cite><span class="color5">8</span><span>留言数</span></cite></td>
                        </tr>
                        <!--
                        <tr>
                          <td width="33.3%"><i class="tall-icon4"></i><cite><span class="color4">0</span><span>评论数</span></cite></td>
                          <td width="33.3%"><i class="tall-icon3"></i><cite><span class="color3">6</span><span>待审核文章</span></cite></td>
                          <td width="33.3%"><i class="tall-icon6"></i><cite><span class="color6">0</span><span>注册人数</span></cite></td>
                        </tr>
                        -->
                    </table>
                </div>
                <div class="fs14 blue fb mb10">访问量分析</div>
                <div class="tabs"> <a href="#" class="on">今日</a> <a href="#">本周</a> <a href="#"> 本月</a> <a href="#">今年</a> </div>
                <div class="visit-box">
                    <table width="100%" style=" border-collapse: collapse; margin-bottom:30px;" >
                        <tr>
                            <td width="33.3%">暂不开通</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="dashboard-right">
                <div class="fs14 blue fb mb10">栏目访问量统计</div>
                <div class="tabs"> <a href="#" class="on">今日</a> <a href="#">本周</a> <a href="#"> 本月</a> <a href="#">总计</a> </div>
                <div class="column-box">
                    <div class="nub-order">
                        <dl>
                            <dt><i class="nuball nub1">1</i>新闻</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub2">2</i>产品</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub3">3</i>案列</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub">4</i> 视频</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub">5</i>招聘</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub">6</i> 科技</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub">7</i>下载</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub">8</i>网络调查</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub">9</i>留言板</dt>
                            <dd>10</dd>
                        </dl>
                        <dl>
                            <dt><i class="nuball nub">10</i>订单</dt>
                            <dd>10</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--  右边主体 E-->

    <!--  底部 S-->
    <!--

    -->
    <!--  底部 E-->
</div>
</body>
</html>