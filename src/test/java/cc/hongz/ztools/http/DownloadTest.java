package cc.hongz.ztools.http;

import cc.hongz.ztools.core.io.FileUtil;
import cc.hongz.ztools.core.io.IORuntimeException;
import cc.hongz.ztools.core.io.StreamProgress;
import cc.hongz.ztools.core.lang.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * 下载单元测试.
 *
 * @author looly
 */
@DisplayName("下载单元测试")
public class DownloadTest {

    @Test
    @Disabled
    @DisplayName("测试下载图片")
    public void downloadPicTest() {
        final String url = "http://wx.qlogo.cn/mmopen/vKhlFcibVUtNBVDjcIowlg0X8aJfHXrTNCEFBukWVH9ta99pfEN88lU39MKspCUCOP3yrFBH3y2NbV7sYtIIlon8XxLwAEqv2/0";
        HttpUtil.downloadFile(url, "e:/pic/t3.jpg");
        Console.log("ok");
    }

    @Test
    @Disabled
    @DisplayName("测试异步下载并写入文件")
    public void downloadSizeTest() {
        final String url = "https://res.t-io.org/im/upload/img/67/8948/1119501/88097554/74541310922/85/231910/366466 - 副本.jpg";
        HttpRequest.get(url).setSSLProtocol("TLSv1.2").executeAsync().writeBody("e:/pic/366466.jpg");
    }

    @Test
    @Disabled
    @DisplayName("测试下载文件返回大小")
    public void downloadTest1() {
        final long size = HttpUtil.downloadFile("http://explorer.bbfriend.com/crossdomain.xml", "e:/temp/");
        System.out.println("Download size: " + size);
    }

    @Test
    @Disabled
    @DisplayName("测试带进度显示的文件下载")
    public void downloadTest() {
        // 带进度显示的文件下载
        HttpUtil.downloadFile("http://mirrors.sohu.com/centos/7/isos/x86_64/CentOS-7-x86_64-DVD-2009.iso", FileUtil.file("d:/"), new StreamProgress() {

            final long time = System.currentTimeMillis();

            @Override
            public void start() {
                Console.log("开始下载。。。。");
            }

            @Override
            public void progress(final long contentLength, final long progressSize) {
                final long speed = progressSize / (System.currentTimeMillis() - time) * 1000;
                Console.log("总大小:{}, 已下载：{}, 速度：{}/s", FileUtil.readableFileSize(contentLength),
                        FileUtil.readableFileSize(progressSize), FileUtil.readableFileSize(speed));
            }

            @Override
            public void finish() {
                Console.log("下载完成！");
            }
        });
    }

    @Test
    @Disabled
    @DisplayName("测试从 URL 下载文件")
    public void downloadFileFromUrlTest1() {
        final File file = HttpUtil.downloadFileFromUrl("http://groovy-lang.org/changelogs/changelog-3.0.5.html", "d:/download/temp");
        assertNotNull(file);
        assertTrue(file.isFile());
        assertTrue(file.length() > 0);
    }

    @Test
    @Disabled
    @DisplayName("测试带进度下载文件（含异常处理）")
    public void downloadFileFromUrlTest2() {
        File file = null;
        try {
            file = HttpUtil.downloadFileFromUrl("https://repo1.maven.org/maven2/cn/hutool/hutool-all/5.4.0/hutool-all-5.4.0-sources.jar", FileUtil.file("d:/download/temp"), 1, new StreamProgress() {
                @Override
                public void start() {
                    System.out.println("start");
                }

                @Override
                public void progress(final long contentLength, final long progressSize) {
                    System.out.println("download size:" + progressSize);
                }

                @Override
                public void finish() {
                    System.out.println("end");
                }
            });

            assertNotNull(file);
            assertTrue(file.exists());
            assertTrue(file.isFile());
            assertTrue(file.length() > 0);
            assertTrue(file.getName().length() > 0);
        } catch (final Exception e) {
            assertTrue(e instanceof IORuntimeException);
        } finally {
            FileUtil.del(file);
        }
    }

    @Test
    @Disabled
    @DisplayName("测试带进度下载文件")
    public void downloadFileFromUrlTest3() {
        File file = null;
        try {
            file = HttpUtil.downloadFileFromUrl("https://repo1.maven.org/maven2/cn/hutool/hutool-all/5.4.0/hutool-all-5.4.0-sources.jar", FileUtil.file("d:/download/temp"), new StreamProgress() {
                @Override
                public void start() {
                    System.out.println("start");
                }

                @Override
                public void progress(final long contentLength, final long progressSize) {
                    System.out.println("contentLength:" + contentLength + "download size:" + progressSize);
                }

                @Override
                public void finish() {
                    System.out.println("end");
                }
            });

            assertNotNull(file);
            assertTrue(file.exists());
            assertTrue(file.isFile());
            assertTrue(file.length() > 0);
            assertTrue(file.getName().length() > 0);
        } finally {
            FileUtil.del(file);
        }
    }

    @Test
    @Disabled
    @DisplayName("测试下载文件（异常场景）")
    public void downloadFileFromUrlTest4() {
        File file = null;
        try {
            file = HttpUtil.downloadFileFromUrl("http://groovy-lang.org/changelogs/changelog-3.0.5.html", FileUtil.file("d:/download/temp"), 1);

            assertNotNull(file);
            assertTrue(file.exists());
            assertTrue(file.isFile());
            assertTrue(file.length() > 0);
            assertTrue(file.getName().length() > 0);
        } catch (final Exception e) {
            assertTrue(e instanceof IORuntimeException);
        } finally {
            FileUtil.del(file);
        }
    }


    @Test
    @Disabled
    @DisplayName("测试下载文件到随机目录")
    public void downloadFileFromUrlTest5() {
        File file = null;
        try {
            file = HttpUtil.downloadFileFromUrl("http://groovy-lang.org/changelogs/changelog-3.0.5.html", FileUtil.file("d:/download/temp", UUID.randomUUID().toString()));

            assertNotNull(file);
            assertTrue(file.exists());
            assertTrue(file.isFile());
            assertTrue(file.length() > 0);
        } finally {
            FileUtil.del(file);
        }

        File file1 = null;
        try {
            file1 = HttpUtil.downloadFileFromUrl("http://groovy-lang.org/changelogs/changelog-3.0.5.html", FileUtil.file("d:/download/temp"));

            assertNotNull(file1);
            assertTrue(file1.exists());
            assertTrue(file1.isFile());
            assertTrue(file1.length() > 0);
        } finally {
            FileUtil.del(file1);
        }
    }

    @Test
    @Disabled
    @DisplayName("测试多次重定向下载")
    public void downloadTeamViewerTest() throws IOException {
        // 此URL有3次重定向, 需要请求4次
        final String url = "https://download.teamviewer.com/download/TeamViewer_Setup_x64.exe";
        HttpGlobalConfig.setMaxRedirectCount(20);
        final Path temp = Files.createTempFile("tmp", ".exe");
        final File file = HttpUtil.downloadFileFromUrl(url, temp.toFile());
        Console.log(file.length());
    }
}
