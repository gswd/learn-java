package netty.customProtocol.t_01.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter@Setter
public class Message {

	private int magicNumber;
	private byte mainVersion;
	private byte subVersion;
	private byte modifyVersion;
	private String sessionId;

	private MessageTypeEnum messageType;
	private Map<String, String> attachments = new HashMap<>();
	private String body;

	public Map<String, String> getAttachments() {
		return Collections.unmodifiableMap(attachments);
	}

	public void setAttachments(Map<String, String> attachments) {
		this.attachments.clear();
		if (null != attachments) {
			this.attachments.putAll(attachments);
		}
	}

	public void addAttachment(String key, String value) {
		attachments.put(key, value);
	}
}
