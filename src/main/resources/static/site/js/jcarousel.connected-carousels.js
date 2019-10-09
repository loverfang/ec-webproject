
    $(function() {
        var carouselNavigation = $('.carousel-navigation').jcarousel();
        
 		carouselNavigation.jcarousel('items').each(function() {
            var item = $(this);
			item.on({
			    click:function(){	
					item.addClass('active').siblings().removeClass("active");
					//点击时设置选中用户ID
					$("#wid").val(item.attr("data"));
					//点击人员的时候加载调整后日期的日历
		            loadSchedule();
		            $("#calendarlistZone").html("");
				}
			})
			
			if(item.attr("class")=="active"){
			     carouselNavigation.jcarousel('scrollIntoView', item);
                 //默认进入页面时设置选中用户ID
				 $("#wid").val(item.attr("data"));
				 //点击人员的时候加载调整后日期日历
		         loadSchedule();
			}
        });
        
        // Setup controls for the navigation carousel
        $('.prev-navigation')
            .on('jcarouselcontrol:inactive', function() {
                $(this).removeClass("active").addClass('inactive');
            })
            .on('jcarouselcontrol:active', function() {
                $(this).removeClass('inactive').addClass("active");
            })
            .jcarouselControl({
                target: '-=1'
            });

        $('.next-navigation')
            .on('jcarouselcontrol:inactive', function() {
                 $(this).removeClass("active").addClass('inactive');
            })
            .on('jcarouselcontrol:active', function() {
                $(this).removeClass('inactive').addClass("active");
            })
            .jcarouselControl({
                target: '+=1'
            });
        
        $("#next").on({
            click:function(){
	            var currentMonth =$("#month");
		        var monthValue = currentMonth.val();
		        if(monthValue < 12){
		            currentMonth.val(parseInt(monthValue) + 1);
		        }else{
		            currentMonth.val(1);
		            var currentYear =$("#year");
		            var yearValue = currentYear.val();
		            currentYear.val(parseInt(yearValue) + 1);
		        }
		        
		        //月增加时加载表格信息
		        loadSchedule();
            }
        });
        
        $("#prev").on({
            click:function(){
		        var currentMonth =$("#month");
		        var monthValue = currentMonth.val();
		        if(monthValue >1){
		        	currentMonth.val(parseInt(monthValue) - 1);
		        }else{
		            currentMonth.val(12);
		            var currentYear =$("#year");
		            var yearValue = currentYear.val();
		            currentYear.val(parseInt(yearValue) - 1);
		        }
		        //月减少时加载表格信息
		        loadSchedule();
            }
        }); 
    });
    
    function loadSchedule(){
        var year = $("#year").val();
        var month = $("#month").val();
        var wid = $("#wid").val();
        
        $(".calendar").html("").html("<div align='center' style='width:560px;height:258px;'>数据加载中...</div>");
        
        $.ajax({
            url:'/worker/ajax_schedules',
            data:{"id":wid,"year":year,"month":parseInt(month) - 1},
            success:function(data){
            	$(".calendar").html("").html(data);
            }
        }); 
    }
    