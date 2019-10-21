package com.goodcub.vci.controller.site;

import com.goodcub.common.upload.FileuploadUtil;
import com.goodcub.vci.service.site.VciFilesFrontService;
import com.goodcub.vci.vo.site.VciFileFrontVO;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/21
 * @Version V1.0
 **/
@Controller
public class DownloadController {

    private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @Resource
    VciFilesFrontService vciFilesFrontService;

    /**
     * 通用文件下载
     * @param fileId
     * @param response
     * @throws Exception
     */
    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response, Long fileId) throws Exception {

        // 根据fileId查询出其对应的路径 和 原生文件名
        VciFileFrontVO vciFile = vciFilesFrontService.queryVciFile(fileId);
        if(vciFile != null) {
            // 文件的实际路径
            String serverPath = FileuploadUtil.getServerPath( vciFile.getFilePath() );
            logger.info("文件的实际路径" + serverPath);

            // 配置文件下载
            // 下载文件能正常显示中文
            String filename = URLEncoder.encode(vciFile.getName(), "UTF-8");

            File file = new File(serverPath);
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/octet-stream");
            OutputStream toClient = null;
            InputStream fis = null;

            //打开文件输入流 和 servlet输出流
            try {
                toClient = new BufferedOutputStream(response.getOutputStream());
                fis = new BufferedInputStream(new FileInputStream(file));
                //通过ioutil 对接输入输出流，实现文件下载
                IOUtils.copy(fis, toClient);
                toClient.flush();
            } catch (Exception e) {
                logger.error("【文件下载失败】", e);
                // throw new RuntimeException("文件下载失败");
            } finally {
                //关闭流
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(toClient);
            }













            //更具文件Id查询出文件路径,然后下载该文件
//            response.setCharacterEncoding(request.getCharacterEncoding());
//            response.setContentType("application/octet-stream");
//            FileInputStream fis = null;
//            try {
//                File file = new File(serverPath);
//                fis = new FileInputStream(file);
//                response.setHeader("Content-Disposition", "attachment; filename=" + filename);
//                IOUtils.copy(fis, response.getOutputStream());
//                response.flushBuffer();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (fis != null) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        }

    }
}
