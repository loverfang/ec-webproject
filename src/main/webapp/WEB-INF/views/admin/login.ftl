<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录-Vci网站管理系统</title>
    <!--必要样式-->
    <link href="${basePath}/static/login/css/styles.css" rel="stylesheet" type="text/css" />
    <script src="${basePath}/static/login/js/jquery-2.1.1.min.js" ></script>
    <script src="${basePath}/static/login/js/jquery-ui.min.js" ></script>
    <link href="${basePath}/static/login/layui/css/layui.css" rel="stylesheet" type="text/css" />
    <script src="${basePath}/static/login/layui/layui.js" type="text/javascript" ></script>
    <script src="${basePath}/static/login/js/canvas-particle.js" type="text/javascript"></script>
    <script type="javascript">
        if(window.top!==window.self){window.top.location=window.location};
    </script>
</head>

<body id="mydiv">
<div class="login-bottom">
    <div class="login-left">
        <div class="login-title">
            <span class="line"></span><span class="tex">Vci网站管理系统</span>
        </div>
        <div class="login-subheading">
            <span class="span-border">让您的网站管理更加轻松!</span>
        </div>
        <div class="login-list">
            <ul>
                <li>上海韦司埃供应链管理有限公司</li>
                <li>--是一家专注于供应链领域的营销服务专家。</li>
                <li>--每年会举办20+场的高端活动。</li>
                <li>--通过活动的组织策划和邀约，帮助服务商获得精准的客户。</li>
                <li>--提供软文报告和专业咨询等服务。</li>
            </ul>
        </div>
    </div>
</div>

<div class='login'>
    <img class="MyLogo" src="${basePath}/static/login/img/logo.png" alt="LOGO" width="126">
    <!-- <div class='login_title'>
        <span>欢迎使用</span>
    </div> -->
    <div class='login_fields'>
        <div class='login_fields__user'>
            <div class='icon'>
                <img alt="" src='${basePath}/static/login/img/user_icon_copy.png'>
            </div>
            <input name="login" placeholder='用户名' maxlength="16" class="username" type='text' autocomplete="off" value=""/>
            <div class='validation'>
                <img alt="" src='${basePath}/static/login/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
                <img alt="" src='${basePath}/static/login/img/lock_icon_copy.png'>
            </div>
            <input name="pwd" class="passwordNumder" placeholder='密码' maxlength="16" type='text' autocomplete="off">
            <div class='validation'>
                <img alt="" src='${basePath}/static/login/img/tick.png'>
            </div>
        </div>
        <div class='login_fields__submit'>
            <input type='button' value='登录'>
        </div>
    </div>
    <div class='success'>
    </div>
    <div class='disclaimer'>
        <p>欢迎使用</p>
    </div>
</div>
<div class='authent'>
    <div class="loader" style="height: 60px;width: 60px;margin-left: 28px;margin-top: 40px">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <p>认证中...</p>
</div>
<div class="OverWindows"></div>
<script type="text/javascript">
    window.onload = function() {
        //配置
        var config = {
            vx: 4,	//小球x轴速度,正为右，负为左
            vy: 4,	//小球y轴速度
            height: 2,	//小球高宽，其实为正方形，所以不宜太大
            width: 2,
            count: 200,		//点个数
            color:"255,255,255",
            stroke:"255,255,255",
            // color: "121, 162, 185", 	//点颜色
            // stroke: "130,255,255", 		//线条颜色
            dist: 6000, 	//点吸附距离
            e_dist: 20000, 	//鼠标吸附加速距离
            max_conn: 10 	//点到点最大连接数
        }

        //调用
        CanvasParticle(config);
    };

    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            $('input[type="button"]').click();
        }
    });

    $('input[name="pwd"]').focus(function () {
        $(this).attr('type', 'password');
    });
    $('input[type="text"]').focus(function () {
        $(this).prev().animate({ 'opacity': '1' }, 200);
    });
    $('input[type="text"],input[type="password"]').blur(function () {
        $(this).prev().animate({ 'opacity': '.5' }, 200);
    });
    $('input[name="login"],input[name="pwd"]').keyup(function () {
        var Len = $(this).val().length;
        if (!$(this).val() == '' && Len >= 5) {
            $(this).next().animate({
                'opacity': '1',
                'right': '30'
            }, 200);
        } else {
            $(this).next().animate({
                'opacity': '0',
                'right': '20'
            }, 200);
        }
    });
    var open = 0;
    layui.use('layer', function () {
        //非空验证
        $('input[type="button"]').click(function () {
            var login = $('.username').val();
            var pwd = $('.passwordNumder').val();
            var code = $('.ValidateNum').val();
            if (login == '') {
                ErroAlert('请输入您的账号');
                return false;
            } else if (pwd == '') {

                ErroAlert('请输入密码');
                return false;
            } else {
                //认证中..
                //fullscreen();
                $('.login').addClass('test'); //倾斜特效
                setTimeout(function () {
                    $('.login').addClass('testtwo'); //平移特效
                }, 300);
                setTimeout(function () {
                    $('.authent').show().animate({ right: -320 }, {
                        easing: 'easeOutQuint',
                        duration: 600,
                        queue: false
                    });
                    $('.authent').animate({ opacity: 1 }, {
                        duration: 200,
                        queue: false
                    }).addClass('visible');
                }, 500);

                //登陆
                var JsonData = { username: login, password: pwd };
                //此处做为ajax内部判断
                $.ajax({
                    type: "post",
                    url:"${basePath}/manage/mlogin",
                    data: JsonData,
                    dataType: 'json',
                    async: 'false',
                    error: function(XMLHttpRequest, textStatus, errorThrown){ ErroAlert(textStatus+":"+errorThrown); },
                    success: function (data) {
                        //ajax返回
                        //认证完成
                        setTimeout(function () {
                            $('.authent').show().animate({ right: 90 }, {
                                easing: 'easeOutQuint',
                                duration: 600,
                                queue: false
                            });
                            $('.authent').animate({ opacity: 0 }, {
                                duration: 200,
                                queue: false
                            }).addClass('visible');
                            $('.login').removeClass('testtwo'); //平移特效
                        }, 2000);
                        setTimeout(function () {
                            $('.authent').hide();
                            $('.login').removeClass('test');
                            if (data.code == 0) {
                                //登录成功
                                $('.login div').fadeOut(100);
                                $('.success').fadeIn(1000);
                                $('.success').html(data.msg);
                                window.top.location = "index";

                            } else {
                                ErroAlert(data.msg);
                            }
                        }, 2400);
                    }
                });
            }
            return false;
        })
    })
    var fullscreen = function () {
        elem = document.body;
        if (elem.webkitRequestFullScreen) {
            elem.webkitRequestFullScreen();
        } else if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.requestFullScreen) {
            elem.requestFullscreen();
        } else {
            //浏览器不支持全屏API或已被禁用
        }
    };

    //弹出
    function ErroAlert(e) {
        var index = layer.alert(e, { icon: 5, time: 2000, offset: 't', closeBtn: 0, title: '错误信息', btn: [], anim: 2, shade: 0 });
        layer.style(index, {
            color: '#777'
        });
    }

</script>
</body>
</html>