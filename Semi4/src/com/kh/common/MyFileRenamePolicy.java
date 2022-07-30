package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	// 반드시 미완성된 rename이라는 추상메소드를 오버라이딩해서 구현해야한다.
	// 기존의 파일을 전달받아서 파일명 수정작업 후에 수정된파일을 반환시킬것
	// 매개변수 : file 객체
	// 리턴값 :file 객체

	@Override
	public File rename(File originFile) {
		// 원본 파일명 뽑기 -> 매개변수로 전달받은 원본파일의 이름을 뽑아냄
		String originName = originFile.getName();

		// 수정파일명 -> 최대한 이름이 겹치지 않도록
//		파일이 업로드된 시간 년, 월, 일, 시, 분, 초  + 5자리 랜덤값 + 확장자
//		확장자 : 원본파일의 확장자 그대로 
//		1.파일이 업로드된 시간 추출 -> String currentTime
//		API 시간에 배웠던 SimpleDateFormat 활용
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// 2. 5자리 랜덤값 추출 -> int ranNum
		int ranNum = (int) (Math.random() * 90000) + 10000;

//		확장자 뽑기 -> String ext
//		String 클래스의 lastIndexOf 메소드 활용

		String ext = originName.substring(originName.lastIndexOf("."));

		String changeName = "Semi4_" + currentTime + "_" + ranNum + ext;

		return new File(originFile.getParent(), changeName); // getParent() == 원본 파일의 디렉토리를 알아내는법

	}
}
