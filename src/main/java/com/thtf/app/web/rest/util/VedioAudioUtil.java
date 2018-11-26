package com.thtf.app.web.rest.util;

import static java.nio.file.StandardOpenOption.READ;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VedioAudioUtil {
	private final static Logger log = LoggerFactory.getLogger(StreamUtil.class);

	
	/*
	 * vedio decode to mp4 h264
	 *  对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),  
     *  可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式. 此方法暂时不支持
	 */
	public static void vedioDecodeToMp4(String fullName){
		log.info(fullName+":  解码中...");
		String newFile = fullName.substring(0,fullName.lastIndexOf(".")+1)+"mp4";
		String cmd = "ffmpeg -i "+fullName+" -c:v libx264 -strict -2 "+newFile;
		
//		List<String> cmds = new ArrayList<String>();
//		cmds.add("E:\\code\\thtf\\ffmpeg\\bin\\ffmpge");
//		cmds.add("-i");
//		cmds.add(fullName);
//		cmds.add("-c:v");
//		cmds.add("libx264");
//		cmds.add("-strict");
//		cmds.add("-2");
//		cmds.add(newFile);
		LocalInvokeUtil.executeCommand(cmd );
		FileUtil.deleteFile(fullName);
		log.info(fullName+":  解码完成！");
		
	}
	
	
	
	public static void processVedio(HttpServletRequest request, HttpServletResponse response, String videoPath,
			String fileName) throws IOException {
		final int BUFFER_LENGTH = 1024 * 16;
		final long EXPIRE_TIME = 1000 * 60 * 60 * 24L;
		final Pattern RANGE_PATTERN = Pattern.compile("bytes=(?<start>\\d*)-(?<end>\\d*)");
		Path video = Paths.get(videoPath, fileName);
		int length = (int) Files.size(video);
		int start = 0;
		int end = length - 1;
		String range = request.getHeader("Range");
		range = range == null ? "" : range;
		Matcher matcher = RANGE_PATTERN.matcher(range);

		if (matcher.matches()) {
			String startGroup = matcher.group("start");
			start = startGroup.isEmpty() ? start : Integer.valueOf(startGroup);
			start = start < 0 ? 0 : start;
			String endGroup = matcher.group("end");
			end = endGroup.isEmpty() ? end : Integer.valueOf(endGroup);
			end = end > length - 1 ? length - 1 : end;
		}

		int contentLength = end - start + 1;

		response.reset();
		response.setBufferSize(BUFFER_LENGTH);
		response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", fileName));
		response.setHeader("Accept-Ranges", "bytes");
		response.setDateHeader("Last-Modified", Files.getLastModifiedTime(video).toMillis());
		response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
		response.setContentType(Files.probeContentType(video));
		response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
		response.setHeader("Content-Length", String.format("%s", contentLength));
		response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

		int bytesRead;
		int bytesLeft = contentLength;
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);

		try (SeekableByteChannel input = Files.newByteChannel(video, READ);
				OutputStream output = response.getOutputStream()) {

			input.position(start);

			while ((bytesRead = input.read(buffer)) != -1 && bytesLeft > 0) {
				buffer.clear();
				output.write(buffer.array(), 0, bytesLeft < bytesRead ? bytesLeft : bytesRead);
				bytesLeft -= bytesRead;
			}
		} catch (Exception e) {
			log.info("视频传输：", e);
		}
	}
	
	
	
	public static Boolean isVideo(String mime,String suffix){
		String vedioSuffix = ".asx,.asf,.mpg,.wmv,.3gp,.mp4,.mov,.avi,.flv";
		if(mime.startsWith("video")||vedioSuffix.contains(suffix)){
			return true;
		}
		return false;
	}
	
	
	public static Boolean isMp4(String mime){
		
		if(mime.startsWith("video/mp4")){
			return true;
		}
		return false;
	}
	
}
