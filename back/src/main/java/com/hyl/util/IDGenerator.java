package com.hyl.util;

import java.util.UUID;

public class IDGenerator {

	// 返回一个含有32个字符的随机字符串，作为
	// 主键使用。字符串中的字符都是16进制的数字。
	public static String genID() {
		// replaceAll("-", "")是为了删除字符串中的“-”。
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
