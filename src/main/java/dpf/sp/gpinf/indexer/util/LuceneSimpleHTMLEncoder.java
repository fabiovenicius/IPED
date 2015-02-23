package dpf.sp.gpinf.indexer.util;

import org.apache.lucene.search.highlight.Encoder;

/**
 * Copyright 2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Simple {@link Encoder} implementation to escape text for HTML output
 * 
 */
public class LuceneSimpleHTMLEncoder implements Encoder {
	public LuceneSimpleHTMLEncoder() {
	}

	@Override
	public String encodeText(String originalText) {
		return htmlEncode(originalText);
	}

	public final static String htmlDecode(String html) {
		if (html == null || html.length() == 0)
			return "";

		String result = html.replaceAll("&quot;", "\"");
		result = result.replaceAll("&amp;", "&");
		result = result.replaceAll("&lt;", "<");
		result = result.replaceAll("&gt;", ">");

		return result;

	}

	/**
	 * Encode string into HTML
	 */
	public final static String htmlEncode(String plainText) {
		if (plainText == null || plainText.length() == 0) {
			return "";
		}

		StringBuilder result = new StringBuilder(plainText.length());

		char[] chars = new char[plainText.length()];
		plainText.getChars(0, chars.length, chars, 0);

		for (int index = 0; index < plainText.length(); index++) {
			// char ch = plainText.charAt(index);
			char ch = chars[index];

			switch (ch) {
			case '"':
				result.append("&quot;");
				break;

			case '&':
				result.append("&amp;");
				break;

			case '<':
				result.append("&lt;");
				break;

			case '>':
				result.append("&gt;");
				break;

			default:
			// if (ch < 128)
			{
				result.append(ch);
			}
			/*
			 * else { result.append("&#").append((int)ch).append(";"); }
			 */

			}
		}

		return result.toString();
	}
}