package com.goodcub.vci.controller.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.entity.Videos;
import com.goodcub.vci.entity.VideosViewLog;
import com.goodcub.vci.service.site.MemberFrontService;
import com.goodcub.vci.service.site.VideosFrontService;
import com.goodcub.vci.vo.site.MemberFrontVO;
import com.goodcub.vci.vo.site.VideosListFrontVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Controller
public class VideoFrontController {

    private static final Logger logger = LoggerFactory.getLogger(VideoFrontController.class);

    @Resource
    VideosFrontService videosFrontService;

    @Resource
    MemberFrontService memberFrontService;

    /**
     * 查询视频列表信息
     * @param request
     * @param response
     * @param title
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @GetMapping("/videos")
    public String videosList(HttpServletRequest request, HttpServletResponse response,
         @RequestParam(value = "title", required = false)Integer title,
         @RequestParam(value = "page", required = false)Integer page,
         @RequestParam(value = "limit", required = false)Integer limit) throws Exception{

        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect("/");
            return null;
        }else{
            Object tempMem = session.getAttribute("member");
            if(tempMem != null){
                // 初始化分页默认数据
                if(page == null || "".equals(page)){
                    page = 1;
                }
                if(limit == null || "".equals(limit)){
                    limit = 10;
                }

                Map<String, Object> params = new HashMap<>();
                params.put("title", title);

                TableDataInfo tableDataInfo = videosFrontService.queryFrontVideosList(params, page, limit);

                request.setAttribute("data", tableDataInfo);
                request.setAttribute("ntype", "videos");
                return "site/membership";
            }else{
                response.sendRedirect("/");
                return null;
            }
        }

    }

    @Transactional
    @GetMapping("/videos/{vid}.html")
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("vid") Integer vid) throws Exception{

        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect("/member/tologin");
            return null;
        }else{
            //会员信息
            MemberFrontVO member = (MemberFrontVO) session.getAttribute("member");
            if(member!=null){

                // 登录者的历史观看次数
                Map<String,Object> vparam = new HashMap<String, Object>();
                vparam.put("vid", vid);
                vparam.put("memid", member.getMemid());
                Integer viewCount = videosFrontService.queryViewCountByMemid(vparam);

                // 视频信息
                VideosListFrontVO videoInfo = videosFrontService.queryFrontVideoInfo(vid);
                if(videoInfo == null){
                    response.sendRedirect("/videos");
                    logger.info("不存在的视频信息!");
                    return null;
                }

                Videos videos = new Videos();
                videos.setVid(videoInfo.getVid());
                videos.setViewcount(videoInfo.getViewcount() + 1);

                if(viewCount != null && viewCount > 0){
                    // 更新视频观看次数
                    videosFrontService.updateViewCount(videos);

                    // 看过了就直接跳转去看就得了
                    response.sendRedirect(videoInfo.getVurl());
                    return null;
                }else{

                    //首次观看,减少积分
                    MemberFrontVO memberFrontVO = memberFrontService.queryMemberViewCount(member.getMemid());
                    if(videoInfo.getNeedcount() > memberFrontVO.getViewcount()){
                        // 积分太少无法观看
                        response.sendRedirect("/videos");
                        logger.info("积分太少无法观看!");
                        return null;
                    }

                    // 更新视频观看次数
                    videosFrontService.updateViewCount(videos);

                    Members countMembers = new Members();
                    countMembers.setMemid(memberFrontVO.getMemid());
                    countMembers.setViewcount(memberFrontVO.getViewcount() - videoInfo.getNeedcount());
                    memberFrontService.updateMemberViewCount(countMembers);

                    //插入观看记录
                    VideosViewLog viewLogs = new VideosViewLog();
                    viewLogs.setVid(vid);
                    viewLogs.setMemid(member.getMemid());
                    viewLogs.setTitle(videoInfo.getTitle());
                    viewLogs.setVtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
                    viewLogs.setUsecount(videoInfo.getNeedcount());

                    videosFrontService.insertViewLogs(viewLogs);
                    response.sendRedirect(videoInfo.getVurl());
                    return null;
                }
            }else{
                response.sendRedirect("/member/dologin");
                return null;
            }
        }

    }

}
